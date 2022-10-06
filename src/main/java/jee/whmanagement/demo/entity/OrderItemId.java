package jee.whmanagement.demo.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Embeddable
public class OrderItemId implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Item item;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("id")
//    @JoinColumn(name = "order_id")
//    private Order order;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("id")
//    @JoinColumn(name = "item_id")
//    private Item item;

    public OrderItemId() {
    }

}
