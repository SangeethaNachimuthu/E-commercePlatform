package se.lexicon.ecommerceplatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Builder

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 100)
    @ToString.Include
    private String firstName;

    @Column(nullable = false, length = 100)
    @ToString.Include
    private String lastName;

    @Column(nullable = false, unique = true, length = 150)
    @ToString.Include
    private String email;

    @Column
    @ToString.Include
    private Instant createdAt;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true) //Customer owns Address
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)  //Customer owns UserProfile
    @JoinColumn(name = "profile_id")
    private UserProfile profile;

    // Keeps the bidirectional relation consistent (recommended)
    public void setProfile(UserProfile profile) {
        // Break old link
        if (this.profile != null) {
            this.profile.setCustomer(null);
        }

        // Set new link
        this.profile = profile;

        // Maintain inverse side
        if (profile != null) {
            profile.setCustomer(this);
        }
    }

    @PrePersist
    private void prePersist() {
        createdAt = Instant.now();
    }
}
