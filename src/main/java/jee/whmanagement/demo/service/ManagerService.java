package jee.whmanagement.demo.service;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


public interface ManagerService {

    void scheduleDelivery(Date deliveryDate, List<Long> truckIds);
}
