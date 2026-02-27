package se.lexicon.ecommerceplatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String name;

    @ElementCollection  //This annotation creates the collection table.
    @CollectionTable(
            name = "product_images",
            joinColumns = @JoinColumn(name = "product_id")
    )
    @Column(name = "image_url")
    private List<String> imageUrls;

    @Column
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
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
