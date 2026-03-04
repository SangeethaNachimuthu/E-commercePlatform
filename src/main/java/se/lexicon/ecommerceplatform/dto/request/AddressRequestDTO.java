package se.lexicon.ecommerceplatform.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AddressRequestDTO(

        @NotBlank(message = "Street can't be blank")
        String street,

        @NotBlank(message = "City cannot be blank")
        String city,

        @NotBlank(message = "Zipcode cannot be blank")
        String zipcode
) {
}
