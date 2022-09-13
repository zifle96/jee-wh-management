package jee.whmanagement.demo.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ScheduleDeliveryRequest {

    private Date deliveryDate;
    private List<Long> truckIds;

}
