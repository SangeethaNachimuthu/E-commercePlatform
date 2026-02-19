package se.lexicon.ecommerceplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.ecommerceplatform.entity.Customer;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

    List<Customer> findByLastNameIgnoreCase(String lastName);

    List<Customer> findByAddress_City(String city);

    List<Customer> findByEmailContains(String email);

    List<Customer> findByCreatedAtAfter(Instant createdAtAfter);

    List<Customer> findByCreatedAtBetween(Instant createdAtAfter, Instant createdAtBefore);

    boolean existsByAddress_City(String addressCity);

    boolean existsByEmail(String email);
}
