package jee.whmanagement.demo.entity;


import jee.whmanagement.demo.enums.OrderStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@EqualsAndHashCode
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

    private Date submittedDate;
    private Date deadlineDate;
    private Date deliveredDate;
}
