package sbecom.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;
import jakarta.persistence.GenerationType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder // it helps in creating immutable objects and provides a flexible way to construct instances of the class
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Product product;
    private int quantity;
    private int price;
    Order order;
}
