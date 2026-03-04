package se.lexicon.ecommerceplatform.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequestDTO(

        @NotBlank(message = "Name cannot be blank")
        @Size(max = 200, message = "Name must not exceed 200 characters")
        String name,

        @NotBlank(message = "Price cannot be blank")
        @Positive(message = "Price must be greater than 0")
        @Digits(integer = 17, fraction = 2, message = "Price must have max 17 digits before decimal and 2 after")
        BigDecimal price,

        @NotNull(message = "Category Id cannot be null")
        Long categoryId
) {
}
