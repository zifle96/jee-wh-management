package jee.whmanagement.demo.service.impl;

import jee.whmanagement.demo.entity.Item;
import jee.whmanagement.demo.entity.Order;
import jee.whmanagement.demo.entity.Truck;
import jee.whmanagement.demo.enums.OrderStatus;
import jee.whmanagement.demo.mapper.MapperUtil;
import jee.whmanagement.demo.model.ItemDto;
import jee.whmanagement.demo.repository.ItemRepository;
import jee.whmanagement.demo.repository.OrderRepository;
import jee.whmanagement.demo.repository.TruckRepository;
import jee.whmanagement.demo.service.ManagerService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    TruckRepository truckRepository;

    @Override
    public List<ItemDto> getItems() {

        return MapperUtil.mapList(itemRepository.findAll(), ItemDto.class);
    }

    @Override
    public List<ItemDto> createNewItems(List<ItemDto> listOfItems) {
        Set<Item> newItems = new HashSet<>();
        try{
            for (ItemDto itemDto : listOfItems) {
                Item item = new Item();
                item.setItemName(itemDto.getItemName());
                item.setDescription(itemDto.getDescription());
                item.setQuantity(itemDto.getQuantity());
                item.setUnitPrice(itemDto.getUnitPrice());
                newItems.add(item);
            }
            List <Item> savedItems =itemRepository.saveAll(newItems);
            return MapperUtil.mapList(savedItems,ItemDto.class );
        }catch (ServiceException ex){
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public void updateItem(ItemDto item) {
    }

    @Override
    public void deleteItem(Long id) {

    }

//    @Override
//     public void scheduleDelivery(Date deliveryDate, List<Long> truckIds){
//
//         List<Order> orderList = orderRepository.findByOrderStatus(OrderStatus.APPROVED);
//
//         if(orderList != null && orderList.size()>0){
//
//             for (Order order : orderList){
//                 if(order.getItem().size() <= 10){
//
//                 }
//             }
//         }
//     }

     public List<Truck> getAvailableTrucks(List<Long>truckIds){

         List<Truck> truckList= truckRepository.findAllById(truckIds);
         //List<Truck>availableTruckList =

                 return truckList.stream().
                 filter(elem-> elem.isAvailable()).collect(Collectors.toList());
     }

}
