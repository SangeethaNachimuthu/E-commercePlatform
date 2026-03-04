package se.lexicon.ecommerceplatform.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.ecommerceplatform.dto.request.AddressRequestDTO;
import se.lexicon.ecommerceplatform.dto.response.AddressResponseDTO;
import se.lexicon.ecommerceplatform.entity.Address;

@Component
public class AddressMapper {

    public AddressResponseDTO toResponse(Address address) {

        if (address == null) {
            throw new IllegalArgumentException("Address can't be null");
        }

        return new AddressResponseDTO(
                address.getId(),
                address.getStreet(),
                address.getCity(),
                address.getZipcode()
        );
    }

    public Address toAddressEntity(AddressRequestDTO addressRequestDTO) {

        if (addressRequestDTO == null) {
            throw new IllegalArgumentException("Address Request cannot be null");
        }

        Address address = new Address();
        address.setStreet(addressRequestDTO.street());
        address.setCity(addressRequestDTO.city());
        address.setZipcode(addressRequestDTO.zipcode());

        return address;
    }
}
