package jee.whmanagement.demo.model;

import jee.whmanagement.demo.entity.Item;
import jee.whmanagement.demo.entity.Truck;
import jee.whmanagement.demo.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;


import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class OrderResponse {


    private Long id;
    private Integer userId;

    private List<Truck> truck;
    private OrderStatus status;


    private List<Item> item;

    private Timestamp submittedDate;
    private Timestamp deadlineDate;
    private Timestamp deliveredDate;
}
