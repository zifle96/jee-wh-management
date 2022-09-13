package jee.whmanagement.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Integer requestedQuantity;

    @OneToOne(cascade = CascadeType.ALL)
    private Inventory inventory;

    @ManyToMany(mappedBy = "item")
    private Set<Order> order;


}
