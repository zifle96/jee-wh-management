package jee.whmanagement.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode
@Entity
public class Inventory {

      @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
      private Long id;
      private String name;
      private Integer quantity;
      private Float unitPrice;

}
