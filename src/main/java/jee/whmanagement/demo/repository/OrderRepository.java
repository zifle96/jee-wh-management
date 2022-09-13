package jee.whmanagement.demo.repository;

import jee.whmanagement.demo.entity.Order;
import jee.whmanagement.demo.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Query("UPDATE Order SET status = ?1 where user_id = ?2")
    Order setStatus(OrderStatus status, Long userId);
    List<Order> findByUserIdAndStatus(Long userId, OrderStatus orderStatus);
    List<Order> findAllByUserId(Long userId);
    List<Order> findByStatus(OrderStatus orderStatus);
}
