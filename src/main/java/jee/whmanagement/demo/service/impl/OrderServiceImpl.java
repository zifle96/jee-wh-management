package jee.whmanagement.demo.service.impl;


import jee.whmanagement.demo.entity.*;
import jee.whmanagement.demo.enums.OrderStatus;
import jee.whmanagement.demo.mapper.MapperUtil;
import jee.whmanagement.demo.model.*;
import jee.whmanagement.demo.repository.ItemRepository;
import jee.whmanagement.demo.repository.OrderItemRepository;
import jee.whmanagement.demo.repository.OrderRepository;
import jee.whmanagement.demo.repository.UserRepository;
import jee.whmanagement.demo.service.OrderService;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    OrderItemRepository orderItemRepository;



    @Override
    public OrderDto createNewOrder(OrderRequest orderRequest) throws ServiceException {

        try{
            Order order = new Order();
            order.setUser(new User(orderRequest.getUserId()));
            //order.setTruck(new Truck(orderRequest.getTruckId()));
            order.setOrderStatus(OrderStatus.CREATED.toString());
            order.setSubmittedDate(Timestamp.from(Instant.now()));
            order.setDeadlineDate(Timestamp.from(Instant.now().plusMillis(86400000)));
            Order savedOrder=orderRepository.save(order);
            // List<Item> items =itemRepository.findAllById(orderRequest.getItemRequest().stream().map(e-> e.getItemId()).collect(Collectors.toList()));
            Set<OrderItem> orderItemList = new HashSet<>();
            for (ItemRequest itemRequest: orderRequest.getItemRequest()){
                OrderItem orderItem = new OrderItem();
                OrderItemId orderItemId = new OrderItemId();
                Item item = new Item();
                item.setId(itemRequest.getItemId());
                orderItemId.setItem(item);
                orderItemId.setOrder(savedOrder);
                orderItem.setId(orderItemId);
                orderItem.setRequestedQuantity(itemRequest.getRequestedQuantity());
                orderItemList.add(orderItem);
            }
            order.setOrderItem(orderItemList);
            orderItemRepository.saveAll(orderItemList);
            return modelMapper.map(orderRepository.save(order), OrderDto.class);

        } catch (ServiceException ex) {
              throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public List<OrderDto> getOrders(Long userId, OrderStatus orderStatus) throws ServiceException {
        try{
            if (orderStatus != null){
                return MapperUtil.mapList(orderRepository.findByUserIdAndOrderStatus(userId, orderStatus.toString()), OrderDto.class);
            }
            return MapperUtil.mapList(orderRepository.findAllByUserId(userId), OrderDto.class);

        } catch (ServiceException ex){
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public List<OrderItem> addItemsToOrder(Long orderId, List<ItemRequest> itemRequestList) throws ServiceException {

        Optional<Order> order = orderRepository.findById(orderId);

        if (order.isPresent()) {
            if (OrderStatus.CREATED.toString().equals(order.get().getOrderStatus())
                    || OrderStatus.DECLINED.equals(order.get().getOrderStatus())) {
                Set<OrderItem> orderItems = new HashSet<>();
                for (ItemRequest itemRequest : itemRequestList) {

                    OrderItem orderItem = new OrderItem();
                    OrderItemId orderItemId = new OrderItemId();
                    Item item = new Item();
                    item.setId(itemRequest.getItemId());
                    orderItemId.setItem(item);
                    orderItemId.setOrder(new Order(orderId));
                    orderItem.setId(orderItemId);
                    orderItem.setRequestedQuantity(itemRequest.getRequestedQuantity());
                    orderItems.add(orderItem);
                }
                return orderItemRepository.saveAll(orderItems);
            } else {
                throw new ServiceException(String.format("Cant update with id %s , order isnt in status CREATED OR DECLINED", orderId));
            }
        } else {
            throw new ServiceException(String.format("Order with id %s does not exist ", orderId));
        }
    }

    @Override
    public void removeOrderItems(Long orderId, List<ItemRequest> listOfItemsId) throws ServiceException {

        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            if (OrderStatus.CREATED.toString().equals(order.get().getOrderStatus())
                    || OrderStatus.DECLINED.equals(order.get().getOrderStatus())) {
                List<OrderItemId> orderItemIdList = listOfItemsId.stream().map(e -> new OrderItemId(new Order(order.get().getId())
                        , new Item(e.getItemId()))).collect(Collectors.toList());
                List<OrderItem> orderItemList = orderItemRepository.findByIdIn(orderItemIdList);
                orderItemRepository.deleteAll(orderItemList);
            }
            else {
                throw new ServiceException(String.format("Cant update with id %s , order isnt in status CREATED OR DECLINED", orderId));
            }
        } else {
            throw new ServiceException(String.format("Cannot CANCEL order with id %s Order doesnt exists", orderId));
        }
    }

    @Override
    public List<OrderItemDto> updateOrderModifyItems(Long orderId, List<ItemRequest> itemRequestList) throws ServiceException {

        Optional<Order> order = orderRepository.findById(orderId);

        if (order.isPresent()) {
            if (OrderStatus.CREATED.toString().equals(order.get().getOrderStatus())
                    || OrderStatus.DECLINED.equals(order.get().getOrderStatus())) {
                List<OrderItemId> orderItemIdList = itemRequestList.stream().map(e -> new OrderItemId(new Order(order.get().getId()), new Item(e.getItemId()))).collect(Collectors.toList());
                List<OrderItem> orderItemList= orderItemRepository.findByIdIn(orderItemIdList);
                for (OrderItem orderItem : orderItemList){
                    for (ItemRequest itemRequest : itemRequestList){
                        if(orderItem.getId().getItem().getId().equals(itemRequest.getItemId())){
                            orderItem.setRequestedQuantity(itemRequest.getRequestedQuantity());
                        }
                    }
                }
                return MapperUtil.mapList(orderItemRepository.saveAll(orderItemList),OrderItemDto.class);
            } else {
                throw new ServiceException(String.format("Cant update with id %s , order isnt in status CREATED OR DECLINED", orderId));
            }
        } else {
            throw new ServiceException(String.format("Order with id %s does not exist ", orderId));
        }
    }

    @Transactional
    @Override
    public void cancelOrder(Long orderId) throws ServiceException {

       Long userId = 1L;
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            if (!(OrderStatus.CANCELLED.equals(order.get().getOrderStatus()) || OrderStatus.FULFILLED.equals(order.get().getOrderStatus())
                    || OrderStatus.UNDER_DELIVERY.equals(order.get().getOrderStatus()))) {

                orderRepository.setOrderStatus(OrderStatus.CANCELLED.toString(), userId, orderId);
            }
            else{
                throw new ServiceException(String.format("Cant update with id %s , order is in statuss CREATED OR FULFILLED OR UNDER_DELIVERY ", orderId));
            }
        } else {
            throw new ServiceException(String.format("Cannot CANCEL order with id %s Order doesnt exists", orderId));
        }
    }
}
