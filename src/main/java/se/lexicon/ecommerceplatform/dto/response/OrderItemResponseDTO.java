package se.lexicon.ecommerceplatform.dto.response;

import java.math.BigDecimal;

public record OrderItemResponseDTO(

        Long productId,
        int quantity,
        BigDecimal priceAtPurchase
) {
}
