package jee.whmanagement.demo.resource;

import jee.whmanagement.demo.model.ItemRequest;
import jee.whmanagement.demo.entity.Order;
import jee.whmanagement.demo.enums.OrderStatus;
import jee.whmanagement.demo.model.OrderDto;
import jee.whmanagement.demo.model.OrderRequest;
import jee.whmanagement.demo.service.OrderService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client-order")
public class ClientResource {

    @Autowired
     OrderService orderService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public OrderDto createNewOrder(@RequestBody OrderDto orderDto) throws ServiceException {

        return orderService.createNewOrder(orderDto);

    }

//    @PutMapping(value = "/update/{orderId}")
//    public Order updateOrder(@RequestBody List<ItemRequest> itemRequestList,
//                             @PathVariable Long orderId) throws ServiceException{
//
//        return orderService.updateOrder(orderId, itemRequestList);
//    }

//    @PutMapping(value = "/remove-item/{orderId}")
//    public void removeOrderItems(@RequestBody List<ItemRequest> itemRequestList,
//                             @PathVariable Long orderId) throws ServiceException{
//
//         orderService.removeOrderItems(orderId,itemRequestList);
//    }


    @GetMapping(value = "/cancel/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Order cancelOrder(@PathVariable Long orderId) throws Exception {

        return orderService.cancelOrder(orderId);
    }

    @GetMapping(value = {"/{userId}", "/{userId}/{filterStatus}"} , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getOrders(@PathVariable Long userId, @PathVariable (required = false) OrderStatus filterStatus) throws Exception {

        return orderService.getOrders(userId, filterStatus);
    }
//    @PutMapping(value = "/submit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Integer> subminOrder(
//            @RequestBody List<ItemRequest> itemRequestList) throws Exception {
//
//        orderService.createNewOrder(itemRequestList);
//        return ResponseEntity.ok(orderService.createNewOrder(itemRequestList));
//    }
}
