package jee.whmanagement.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "order_item")
@Entity
public class OrderItem {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "requested_quantity")
    private Integer requestedQuantity;

    public OrderItem(Long id, Order order, Item item, Integer requestedQuantity) {
        this.id = id;
        this.order = order;
        this.item = item;
        this.requestedQuantity = requestedQuantity;
    }
}
