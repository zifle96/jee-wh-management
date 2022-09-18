package jee.whmanagement.demo.resource;

import jee.whmanagement.demo.model.ItemDto;
import jee.whmanagement.demo.model.OrderDto;
import jee.whmanagement.demo.service.ManagerService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerResource {

    @Autowired
    ManagerService managerService;

    @PostMapping(value = "/items-create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ItemDto> createNewItems(@RequestBody List<ItemDto> itemDtoList) throws ServiceException {

        return managerService.createNewItems(itemDtoList);

    }

    @GetMapping(value = "/all-items", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ItemDto> getAllItems() throws ServiceException{

        return managerService.getItems();

    }

}
