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
    @Query("UPDATE Order SET order_status = ?1 where user_id = ?2 AND id = ?3")
    void setOrderStatus(String status, Long userId, Long id);
    List<Order> findByUserIdAndOrderStatus(Long userId, String orderStatus);
    List<Order> findAllByUserId(Long userId);
    List<Order> findByOrderStatus(String orderStatus);
}
