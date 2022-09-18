package jee.whmanagement.demo.service;


import jee.whmanagement.demo.entity.Item;
import jee.whmanagement.demo.model.ItemDto;
import org.hibernate.service.spi.ServiceException;

import java.util.Date;
import java.util.List;


public interface ManagerService {

    List<ItemDto> getItems()throws ServiceException;
    List<ItemDto> createNewItems(List<ItemDto> listOfItems) throws ServiceException;
    void updateItem(ItemDto item) ;
    void deleteItem(Long id);
    //void scheduleDelivery(Date deliveryDate, List<Long> truckIds);
}
