package se.lexicon.ecommerceplatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Setter
    @Column(nullable = false)
    @ToString.Include
    private Instant orderDate;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    @ToString.Include
    private OrderStatus status;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

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
