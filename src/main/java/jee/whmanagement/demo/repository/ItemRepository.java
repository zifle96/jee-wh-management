package jee.whmanagement.demo.repository;

import jee.whmanagement.demo.entity.Item;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
