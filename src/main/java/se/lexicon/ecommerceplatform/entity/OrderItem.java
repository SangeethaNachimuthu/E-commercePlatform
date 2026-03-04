package se.lexicon.ecommerceplatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(nullable = false)
    @ToString.Include
    private Integer quantity;

    @Column(nullable = false, precision = 19, scale = 2)
    @ToString.Include
    private BigDecimal priceAtPurchase;

    @ManyToOne(fetch = FetchType.LAZY)  // Many order items belong to one order
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)  // Many order items reference one product
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
