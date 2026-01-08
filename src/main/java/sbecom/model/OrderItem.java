package sbecom.model;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;
import jakarta.persistence.GenerationType;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder // it helps in creating immutable objects and provides a flexible way to construct instances of the class; 
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;
    private int quantity;
    private int totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    Order order;
}
