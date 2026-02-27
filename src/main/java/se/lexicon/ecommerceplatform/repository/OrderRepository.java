package se.lexicon.ecommerceplatform.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.ecommerceplatform.entity.Order;
import se.lexicon.ecommerceplatform.entity.OrderStatus;

import java.time.Instant;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

    //SELECT * FROM orders WHERE customer_id = ?;
    List<Order> findByCustomer_Id(Long customerId);

    /* The EntityGroup annotation will fetch Orders and their items in one query using a join internally.
        eg. SELECT o.*, i.* FROM orders o
            LEFT JOIN order_items i ON o.id = i.order_id
            WHERE o.status = 'PENDING';
     */
    @EntityGraph(attributePaths = "items")
    List<Order> findByStatus(OrderStatus status);

    //SELECT * FROM orders WHERE order_date > ?;
    List<Order> findByOrderDateAfter(Instant orderDateAfter);

    //SELECT * FROM orders WHERE order_date BETWEEN ? AND ?;
    List<Order> findByOrderDateBetween(Instant orderDateAfter, Instant orderDateBefore);

    //Find orders that contain a specific product.
    //SELECT DISTINCT o.* FROM orders o
    //        JOIN order_items i ON o.id = i.order_id WHERE i.product_id = ?;
    @Query("SELECT DISTINCT o FROM Order o JOIN o.items i WHERE i.product.Id = ?")
    List<Order> findByProductId(@Param("product_id") Long product_id);

    //SELECT COUNT(*) FROM orders WHERE status = ?;
    long countByStatus(OrderStatus status);

    //SELECT * FROM orders WHERE customer_id = ? AND status = ?;
    List<Order> findByCustomer_IdAndStatus(Long customerId, OrderStatus status);
}
