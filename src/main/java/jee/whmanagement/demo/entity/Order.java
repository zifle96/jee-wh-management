package jee.whmanagement.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
@Entity
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="truck_id", referencedColumnName = "id",nullable=false)
    private Truck truck;

    @Column(name = "order_status")
    private String orderStatus;


    //@Column(nullable = false)
    //@Cascade(org.hibernate.annotations.CascadeType.REPLICATE)
//    @ManyToOne
//    @JoinColumn(name="item_id", referencedColumnName = "id",nullable=false)
//    private Item item;
    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItem = new HashSet<OrderItem>();

    @Column(name = "deadline_date")
    private Timestamp deadlineDate;
    @Column(name = "submitted_date")
    private Timestamp submittedDate;
    @Column(name = "deliverable_date")
    private Timestamp deliveredDate;

    public Order(Long id, User user, Truck truck, String orderStatus, Set<OrderItem> orderItem, Timestamp deadlineDate, Timestamp submittedDate, Timestamp deliveredDate) {
        this.id = id;
        this.user = user;
        this.truck = truck;
        this.orderStatus = orderStatus;
        this.orderItem = orderItem;
        this.deadlineDate = deadlineDate;
        this.submittedDate = submittedDate;
        this.deliveredDate = deliveredDate;
    }
}
