package jee.whmanagement.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "trucks")
public class Truck {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "chassis_number")
    private Integer chassisNumber;
    @Column(name = "licence_plate")
    private Integer licencePlate;

    @OneToMany(mappedBy = "truck")
    private Set<Order> order;

    private boolean available;

    public Truck(Long id, Integer chassisNumber, Integer licencePlate, Set<Order> order, boolean available) {
        this.id = id;
        this.chassisNumber = chassisNumber;
        this.licencePlate = licencePlate;
        this.order = order;
        this.available = available;
    }
}
