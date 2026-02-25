package se.lexicon.ecommerceplatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long id;

    @Setter
    @Column(nullable = false)
    private Instant orderDate;

    @Setter
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderItem> items;

    @PrePersist
    private void prePersist() {
        orderDate = Instant.now();
    }

    public void addItems(OrderItem orderItem) {
        if (orderItem == null) {
            throw new IllegalArgumentException("Order can't be null");
        }
        items.add(orderItem);
    }

    public void removeItems(OrderItem orderItem) {
        if (orderItem == null) {
            throw new IllegalArgumentException("Order can't be null");
        }
        items.remove(orderItem);
    }
}
