package jee.whmanagement.demo.entity;


import jee.whmanagement.demo.enums.OrderStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Entity
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer userId;

    @OneToMany(mappedBy = "order")
    private Set<Truck> truck;
    private OrderStatus status;

    @ManyToMany
    @JoinTable(
            name = "order_item",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> item;

    private Timestamp submittedDate;
    private Timestamp deadlineDate;
    private Timestamp deliveredDate;
}
