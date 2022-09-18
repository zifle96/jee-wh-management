//package jee.whmanagement.demo.entity;
//
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Table(name = "inventory")
//@Entity
//public class Inventory {
//
//      @Id
//      @GeneratedValue(strategy = GenerationType.IDENTITY)
//      private Long id;
//
////      @OneToOne(mappedBy = "inventory")
////      private Item item;
//
//      private Integer quantity;
//      @Column(name = "unit_price")
//      private Float unitPrice;
//
//      public Inventory(Long id, Integer quantity, Float unitPrice) {
//            this.id = id;
//            this.quantity = quantity;
//            this.unitPrice = unitPrice;
//      }
//}
