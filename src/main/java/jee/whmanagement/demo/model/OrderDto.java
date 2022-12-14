package jee.whmanagement.demo.model;

import jee.whmanagement.demo.entity.Item;
import jee.whmanagement.demo.entity.OrderItem;
import jee.whmanagement.demo.entity.Truck;
import jee.whmanagement.demo.entity.User;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private Long userId;
    private Long truckId;
    private String orderStatus;
    //private Set<OrderItemDto> orderItemDto;
    private Timestamp deadlineDate;
    private Timestamp submittedDate;
    private Timestamp deliveredDate;

}
