package jee.whmanagement.demo.repository;

import jee.whmanagement.demo.entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {

    //List<Truck> findByIdAndAvailable(Long)
}
