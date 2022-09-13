package jee.whmanagement.demo.service.impl;

import jee.whmanagement.demo.entity.Order;
import jee.whmanagement.demo.entity.Truck;
import jee.whmanagement.demo.enums.OrderStatus;
import jee.whmanagement.demo.repository.OrderRepository;
import jee.whmanagement.demo.repository.TruckRepository;
import jee.whmanagement.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    TruckRepository truckRepository;

     @Override
     public void scheduleDelivery(Date deliveryDate, List<Long> truckIds){

         List<Order> orderList = orderRepository.findByStatus(OrderStatus.APPROVED);

         if(orderList != null && orderList.size()>0){

             for (Order order : orderList){
                 if(order.getItem().size() <= 10){

                 }
             }
         }
     }

     public List<Truck> getAvailableTrucks(List<Long>truckIds){

         List<Truck> truckList= truckRepository.findAllById(truckIds);
         //List<Truck>availableTruckList =

                 return truckList.stream().
                 filter(elem-> elem.isAvailable()).collect(Collectors.toList());
     }

}
