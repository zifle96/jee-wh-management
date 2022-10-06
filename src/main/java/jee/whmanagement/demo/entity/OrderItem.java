package jee.whmanagement.demo.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_item")
@AssociationOverrides({
        @AssociationOverride(name = "id.order",
                joinColumns = @JoinColumn(name = "order_id")),
        @AssociationOverride(name = "id.item",
                joinColumns = @JoinColumn(name = "item_id")) })
@Entity
public class OrderItem {

    @EmbeddedId
    private OrderItemId id= new OrderItemId();

//    @Transient
//    private Order order;
//
//    @Transient
//    private Item item;

    @Column(name = "requested_quantity")
    private Integer requestedQuantity;

}
