package jee.whmanagement.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private Long userId;
    List<Long> itemRequestList;
}
