package jee.whmanagement.demo.service.impl;

import jee.whmanagement.demo.entity.Item;
import jee.whmanagement.demo.entity.Order;
import jee.whmanagement.demo.enums.OrderStatus;
import jee.whmanagement.demo.model.ItemRequest;
import jee.whmanagement.demo.repository.OrderRepository;
import jee.whmanagement.demo.security.SecurityContext;
import jee.whmanagement.demo.service.OrderService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order createNewOrder(List<ItemRequest> itemRequestList) throws ServiceException {
        Order order = new Order();
        order.setStatus(OrderStatus.CREATED);
        Calendar cal = Calendar.getInstance();
        java.util.Date utilDate = cal.getTime();
        order.setSubmittedDate(new Date(utilDate.getTime()));

        List<Item> itemList = new ArrayList<>();
        for (ItemRequest itemRequest : itemRequestList) {
            Item item = new Item();
            item.setId(itemRequest.getId());
            item.setName(itemRequest.getName());
            item.setDescription(itemRequest.getDescription());
            item.setRequestedQuantity(itemRequest.getRequestedQuantity());
            itemList.add(item);
        }
        java.util.Date dt = new java.util.Date();
        LocalDateTime.from(dt.toInstant()).plusDays(2);
        order.setDeadlineDate(new Date(dt.getTime()));

        return orderRepository.save(order);
    }

    @Override
    public Order cancelOrder(Long orderId) throws ServiceException {

       Long userId = SecurityContext.get().getUserId();
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            if (OrderStatus.CANCELLED.equals(order.get().getStatus()) || OrderStatus.FULFILLED.equals(order.get().getStatus())
                    || OrderStatus.UNDER_DELIVERY.equals(order.get().getStatus())) {

                return orderRepository.setStatus(OrderStatus.CANCELLED, userId);
            }
        } else {
            throw new ServiceException(String.format("Cannot CANCEL order with id %s Order doesnt exists", orderId));
        }
        return null;
    }

    @Override
    public void removeOrderItems(Long orderId, List<ItemRequest> itemRequestList) throws ServiceException {

        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            for (ItemRequest itemRequest : itemRequestList) {
                    order.get().getItem().removeIf(elem -> elem.getId().equals(itemRequest.getId()));
            }
            orderRepository.save(order.get());

        } else {
            throw new ServiceException(String.format("Cannot CANCEL order with id %s Order doesnt exists", orderId));
        }
    }

    @Override
    public List<Order> getOrders(Long userId, OrderStatus orderStatus) throws ServiceException {
        if (orderStatus != null){
            return orderRepository.findByUserIdAndStatus(userId, orderStatus);
        }
        return orderRepository.findAllByUserId(userId);
    }


    @Override
    public Order updateOrder(Long orderId, List<ItemRequest> itemRequestList) throws ServiceException {

        Optional<Order> order = orderRepository.findById(orderId);

        if (order.isPresent()) {
            if (OrderStatus.CREATED.equals(order.get().getStatus())
                    || OrderStatus.DECLINED.equals(order.get().getStatus())) {
                for (ItemRequest itemRequest : itemRequestList) {
                    if (order.get().getItem().contains(itemRequest.getId())) {
                        order.get().getItem().stream().forEach(elem ->
                                {
                                    if (elem.getId().equals(itemRequest.getId())) {
                                        elem.setRequestedQuantity(itemRequest.getRequestedQuantity());
                                    }
                                }
                        );
                    }
                }
                return orderRepository.save(order.get());
            } else {
                throw new ServiceException(String.format("Cant update with id %s , order isnt in status CREATED OR DECLINED", orderId));
            }

        } else {
           return  createNewOrder(itemRequestList);
        }
    }
}
