package se.lexicon.ecommerceplatform.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.ecommerceplatform.entity.Order;
import se.lexicon.ecommerceplatform.entity.OrderStatus;

import java.time.Instant;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomer_Id(Long customerId);

    /* The EntityGroup annotation will fetch Orders and their items in one query using a join internally.
        eg. SELECT o.*, i.* FROM orders o
            LEFT JOIN order_items i ON o.id = i.order_id
            WHERE o.status = 'PENDING';
     */
    @EntityGraph(attributePaths = "items")
    List<Order> findByStatus(OrderStatus status);

    List<Order> findByOrderDateAfter(Instant orderDateAfter);

    List<Order> findByOrderDateBetween(Instant orderDateAfter, Instant orderDateBefore);

    //Find orders that contain a specific product.

    long countByStatus(OrderStatus status);

    List<Order> findByCustomer_IdAndStatus(Long customerId, OrderStatus status);
}
