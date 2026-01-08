package sbecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sbecom.model.Order;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Integer> {
    Optional<Order> findByOrderId(String orderId);
}
