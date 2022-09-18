package jee.whmanagement.demo.service.impl;

import jee.whmanagement.demo.entity.Item;
import jee.whmanagement.demo.entity.Order;
import jee.whmanagement.demo.entity.User;
import jee.whmanagement.demo.enums.OrderStatus;
import jee.whmanagement.demo.model.ItemRequest;
import jee.whmanagement.demo.model.OrderDto;
import jee.whmanagement.demo.model.OrderRequest;
import jee.whmanagement.demo.repository.ItemRepository;
import jee.whmanagement.demo.repository.OrderRepository;
import jee.whmanagement.demo.repository.UserRepository;
import jee.whmanagement.demo.security.SecurityContext;
import jee.whmanagement.demo.service.OrderService;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ItemRepository itemRepository;



    @Override
    @Transactional
    public OrderDto createNewOrder(OrderDto orderDto) throws ServiceException {

        try{
        Order order = modelMapper.map(orderDto, Order.class);
        order.setOrderStatus(OrderStatus.CREATED.toString());
        order.setSubmittedDate(Timestamp.from(Instant.now()));
        order.setDeadlineDate(Timestamp.from(Instant.now().plusMillis(86400000)));

        return modelMapper.map(orderRepository.save(order) , OrderDto.class);

        } catch (ServiceException ex) {
              throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public Order cancelOrder(Long orderId) throws ServiceException {

       Long userId = SecurityContext.get().getUserId();
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            if (OrderStatus.CANCELLED.equals(order.get().getOrderStatus()) || OrderStatus.FULFILLED.equals(order.get().getOrderStatus())
                    || OrderStatus.UNDER_DELIVERY.equals(order.get().getOrderStatus())) {

                return orderRepository.setOrderStatus(OrderStatus.CANCELLED, userId);
            }
        } else {
            throw new ServiceException(String.format("Cannot CANCEL order with id %s Order doesnt exists", orderId));
        }
        return null;
    }

//    @Override
//    public void removeOrderItems(Long orderId, List<Long> listOfItemsId) throws ServiceException {
//
//        Optional<Order> order = orderRepository.findById(orderId);
//        if (order.isPresent()) {
//            for (ItemRequest itemRequest : itemRequestList) {
//                    order.get().getItem().removeIf(elem -> elem.getId().equals(itemRequest.getItemId()));
//            }
//            orderRepository.save(order.get());
//
//        } else {
//            throw new ServiceException(String.format("Cannot CANCEL order with id %s Order doesnt exists", orderId));
//        }
//    }

    @Override
    public List<Order> getOrders(Long userId, OrderStatus orderStatus) throws ServiceException {
        if (orderStatus != null){
            return orderRepository.findByUserIdAndOrderStatus(userId, orderStatus);
        }
        return orderRepository.findAllByUserId(userId);
    }


//    @Override
//    public Order updateOrder(Long orderId, List<ItemRequest> itemRequestList) throws ServiceException {
//
//        Optional<Order> order = orderRepository.findById(orderId);
//
//        if (order.isPresent()) {
//            if (OrderStatus.CREATED.equals(order.get().getStatus())
//                    || OrderStatus.DECLINED.equals(order.get().getStatus())) {
//                for (ItemRequest itemRequest : itemRequestList) {
//                    if (order.get().getItem().contains(itemRequest.getItemId())) {
//                        order.get().getItem().stream().forEach(elem ->
//                                {
//                                    if (elem.getId().equals(itemRequest.getItemId())) {
//                                        elem.setRequestedQuantity(itemRequest.getRequestedQuantity());
//                                    }
//                                }
//                        );
//                    }
//                }
//                return orderRepository.save(order.get());
//            } else {
//                throw new ServiceException(String.format("Cant update with id %s , order isnt in status CREATED OR DECLINED", orderId));
//            }
//
//        } else {
//           return  createNewOrder(itemRequestList);
//        }
//    }
}
