package se.lexicon.ecommerceplatform.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderItemRequestDTO(

        @NotNull(message = "Product Id is required")
        Long productId,

        @Min(value = 1, message = "Quantity must be at least 1")
        int quantity
) {
}
