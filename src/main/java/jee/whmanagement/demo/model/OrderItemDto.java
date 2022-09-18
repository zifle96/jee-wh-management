package jee.whmanagement.demo.model;



import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class OrderItemDto {

    private OrderDto order;
    private ItemDto item;
    private Integer requestedQuantity;

    public OrderItemDto(OrderDto order, ItemDto item, Integer requestedQuantity) {
        this.order = order;
        this.item = item;
        this.requestedQuantity = requestedQuantity;
    }
}
