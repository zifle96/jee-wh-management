package jee.whmanagement.demo.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode
@Table(name = "item")
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "item_name")
    private String itemName;
    private String description;
    private Integer quantity;
    @Column(name = "unit_price")
    private Float unitPrice;

    @OneToMany(mappedBy = "id.item")
    private Set<OrderItem> orderItem = new HashSet<>();

    public Item(Long id) {
        this.id = id;
    }
}
