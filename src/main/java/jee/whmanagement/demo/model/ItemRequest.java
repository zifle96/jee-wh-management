package jee.whmanagement.demo.model;

import lombok.Data;

@Data
public class ItemRequest {

    private Long itemId;
    private String name;
    private String description;
    private Integer requestedQuantity;
}
