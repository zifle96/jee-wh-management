package jee.whmanagement.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemIdDto {

    private OrderDto orderDto;
    private ItemDto itemDto;

    public OrderItemIdDto(OrderDto orderDto, ItemDto itemDto) {
        this.orderDto = orderDto;
        this.itemDto = itemDto;
    }
}
