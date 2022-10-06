package jee.whmanagement.demo.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
//@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "orders")
@Entity
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="truck_id", referencedColumnName = "id")
    private Truck truck;

    @Column(name = "order_status")
    private String orderStatus;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> orderItem = new HashSet<>();

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

    public Order(Long id) {
        this.id = id;
    }
}
