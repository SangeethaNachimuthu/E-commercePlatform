package se.lexicon.ecommerceplatform.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(

        @NotBlank(message = "Name cannot be blank")
        String name
) {
}
