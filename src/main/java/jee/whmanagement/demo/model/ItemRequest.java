package jee.whmanagement.demo.model;

import lombok.Data;

@Data
public class ItemRequest {

    private Long id;
    private String name;
    private String description;
    private Integer requestedQuantity;
}
