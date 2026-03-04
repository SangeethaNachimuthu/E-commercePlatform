package se.lexicon.ecommerceplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.ecommerceplatform.entity.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByZipcode(String zipCode);

    //Optional Task
    List<Address> findByCityIgnoreCase(String city);

    List<Address> findByStreetContainingIgnoreCase(String streetKeyWord);

    boolean existsByZipcode(String zipcode);

    List<Address> findByZipcodeStartingWith(String prefix);
}
