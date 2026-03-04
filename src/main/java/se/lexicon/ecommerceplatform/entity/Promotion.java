package se.lexicon.ecommerceplatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "promotions")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    @ToString.Include
    private String code;

    @Column(nullable = false)
    @ToString.Include
    private LocalDate startDate;

    @Column //End date optional
    @ToString.Include
    private LocalDate endDate;

    @ManyToMany(mappedBy = "productsAndPromotions", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();

}
