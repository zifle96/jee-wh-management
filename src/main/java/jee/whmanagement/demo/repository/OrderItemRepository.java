package jee.whmanagement.demo.repository;

import jee.whmanagement.demo.entity.OrderItem;
import jee.whmanagement.demo.entity.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {

    List<OrderItem> findByIdIn(List<OrderItemId> orderItemIdList);
}
