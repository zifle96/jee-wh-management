package jee.whmanagement.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private Long userId;
    private Long truckId;
   List<ItemRequest> itemRequest;
}
