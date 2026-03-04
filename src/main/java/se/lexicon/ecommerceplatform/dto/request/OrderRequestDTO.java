package se.lexicon.ecommerceplatform.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import se.lexicon.ecommerceplatform.entity.OrderItem;

import java.util.List;

public record OrderRequestDTO(

        @NotNull(message = "Customer Id is required")
        Long customerId,

        @NotEmpty(message = "Order must contain at least one item")
        @Valid //Validation for OrderItemRequestDTO
        List<OrderItemRequestDTO> orderItems
) {
}
