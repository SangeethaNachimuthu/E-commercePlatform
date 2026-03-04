package se.lexicon.ecommerceplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.ecommerceplatform.entity.Customer;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmailIgnoreCase(String email);

    List<Customer> findByLastNameIgnoreCase(String lastName);

    List<Customer> findByAddress_CityIgnoreCase(String city);

    //Optional Task
    List<Customer> findByEmailContainingIgnoreCase(String keyword);

    List<Customer> findByCreatedAtAfter(Instant createdAtAfter);

    List<Customer> findByCreatedAtBetween(Instant createdAtAfter, Instant createdAtBefore);

    long countByAddress_CityIgnoreCase(String addressCity);

    boolean existsByEmailIgnoreCase(String email);
}
