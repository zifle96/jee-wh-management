package jee.whmanagement.demo.model;

import lombok.Data;

@Data
public class ItemRequest {

    private Long itemId;
    private Integer requestedQuantity;
}
