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
@Table(name = "trucks")
public class Truck {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer chassisNumber;
    private Integer licencePlate;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order order;

    private boolean available;
}
