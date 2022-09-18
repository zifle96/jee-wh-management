package jee.whmanagement.demo.model;

import jee.whmanagement.demo.entity.OrderItem;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ItemDto {

    private Integer id;
    private String itemName;
    private String description;
    private Integer quantity;
    private Float unitPrice;
    private OrderItem orderItem;
}
