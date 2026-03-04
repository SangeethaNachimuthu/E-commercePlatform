package se.lexicon.ecommerceplatform.dto.response;

import se.lexicon.ecommerceplatform.entity.Address;

import java.time.Instant;

public record CustomerResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        Instant createdAt,
        AddressResponseDTO addressResponse) {
}
