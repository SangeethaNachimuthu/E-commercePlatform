package se.lexicon.ecommerceplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.ecommerceplatform.entity.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByZipcode(String zipCode);
}
