package se.lexicon.ecommerceplatform.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Builder

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(nullable = false, length = 100)
    @ToString.Include
    private String nickname;

    @Column(nullable = false, length = 100)
    @ToString.Include
    private String phoneNumber;

    @Column(length = 500)
    @ToString.Include
    private String bio;

    @OneToOne(mappedBy = "profile") // Inverse side (FK lives on Customer.profile_id)
    private Customer customer;
}
