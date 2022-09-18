package jee.whmanagement.demo.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "item")
@Entity
public class Item {

    @Id
    //@Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "item_name")
    private String itemName;
    private String description;
    private Integer quantity;
    @Column(name = "unit_price")
    private Float unitPrice;

//    @OneToMany(mappedBy="item")
//    private Set<Order> order;
    @OneToMany(mappedBy = "item")
    private Set<OrderItem> orderItem = new HashSet<OrderItem>();


    public Item(Long id, String itemName, String description, Integer quantity, Float unitPrice, Set<OrderItem> orderItem) {
        this.id = id;
        this.itemName = itemName;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.orderItem = orderItem;
    }
}
