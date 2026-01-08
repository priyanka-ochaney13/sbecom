package sbecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sbecom.model.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {
    
}
