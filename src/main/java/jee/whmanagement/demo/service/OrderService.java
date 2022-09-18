package jee.whmanagement.demo.service;

import jee.whmanagement.demo.model.ItemRequest;
import jee.whmanagement.demo.entity.Order;
import jee.whmanagement.demo.enums.OrderStatus;
import jee.whmanagement.demo.model.OrderDto;
import jee.whmanagement.demo.model.OrderRequest;
import org.hibernate.service.spi.ServiceException;

import java.util.List;


public interface OrderService {

    OrderDto createNewOrder(OrderDto OrderDto)throws ServiceException;

    //Order updateOrder(Long orderId , List<ItemRequest> itemRequestList) throws ServiceException;

    Order cancelOrder(Long orderId)throws ServiceException;

    //void removeOrderItems(Long orderId, List<ItemRequest> itemRequestList)throws ServiceException;

    List<Order> getOrders(Long user_id, OrderStatus orderStatus) throws ServiceException;

}
