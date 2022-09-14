package jee.whmanagement.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Inventory {

      @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
      private Long id;
      private String name;
      private Integer quantity;
      private Float unitPrice;

}
