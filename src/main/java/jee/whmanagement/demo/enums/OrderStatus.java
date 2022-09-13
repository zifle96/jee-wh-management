package jee.whmanagement.demo.enums;


import lombok.Getter;

@Getter
public enum OrderStatus {

    CREATED, AWAITING_APPROVAL, APPROVED, DECLINED, UNDER_DELIVERY,
    FULFILLED, CANCELLED;
}