package jee.whmanagement.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
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
