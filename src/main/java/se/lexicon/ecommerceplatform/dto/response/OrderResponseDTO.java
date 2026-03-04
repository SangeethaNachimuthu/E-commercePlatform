package se.lexicon.ecommerceplatform.dto.response;

import java.time.Instant;
import java.util.List;

public record OrderResponseDTO(
        Long id,
        Instant orderDate,
        String Status,
        List<OrderItemResponseDTO> orderItemResponses
) {
}
