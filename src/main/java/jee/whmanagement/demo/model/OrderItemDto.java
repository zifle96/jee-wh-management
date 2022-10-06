package jee.whmanagement.demo.model;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class OrderItemDto {

    private OrderItemIdDto orderItemIdDto;
    private Integer requestedQuantity;

    public OrderItemDto(OrderItemIdDto orderItemIdDto, Integer requestedQuantity) {
        this.orderItemIdDto = orderItemIdDto;
        this.requestedQuantity = requestedQuantity;
    }
}
