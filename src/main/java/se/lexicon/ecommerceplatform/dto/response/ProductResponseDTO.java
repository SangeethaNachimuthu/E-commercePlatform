package se.lexicon.ecommerceplatform.dto.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record ProductResponseDTO(
        Long id,
        String name,
        BigDecimal price,
        String categoryName
) {
}
