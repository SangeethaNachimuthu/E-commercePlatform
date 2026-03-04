package se.lexicon.ecommerceplatform.dto.response;

public record AddressResponseDTO(
        Long id,
        String street,
        String city,
        String zipcode
) {
}
