package se.lexicon.ecommerceplatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long Id;

    @Column(nullable = false, length = 200)
    @ToString.Include
    private String name;

    @Column(nullable = false, precision = 19, scale = 2)
    @ToString.Include
    private BigDecimal price;


    @ElementCollection  //This annotation creates the collection table.
    @CollectionTable(
            name = "product_images",
            joinColumns = @JoinColumn(name = "product_id")
    )
    @Column(name = "image_url", length = 500)
    private List<String> imageUrls = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)  // Many products belong to one category (avoid default EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    @ManyToMany(fetch = FetchType.LAZY) //This annotation creates the mapper table.
    @JoinTable(
            name = "products_promotions",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "promotion_id")
    )
    private Set<Promotion> productsAndPromotions = new HashSet<>();

    public void addPromotions(Promotion promotion) {
        if (promotion == null) {
            throw new IllegalArgumentException("Promotion can't be null");
        }
        productsAndPromotions.add(promotion);
    }

    public void removePromotions(Promotion promotion) {
        if (promotion == null) {
            throw new IllegalArgumentException("Promotion can't be null");
        }
        productsAndPromotions.remove(promotion);
    }

}
