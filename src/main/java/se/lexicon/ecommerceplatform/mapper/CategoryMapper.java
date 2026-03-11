package se.lexicon.ecommerceplatform.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.ecommerceplatform.dto.request.CategoryRequestDTO;
import se.lexicon.ecommerceplatform.dto.response.CategoryResponseDTO;
import se.lexicon.ecommerceplatform.entity.Category;

@Component
public class CategoryMapper {

    public CategoryResponseDTO toCategoryResponse(Category category) {

        if (category == null)
            throw new IllegalArgumentException("Category cannot be null");

        return new CategoryResponseDTO(
                category.getId(),
                category.getName()
        );
    }

    public Category toCategoryEntity(CategoryRequestDTO requestDTO) {

        if (requestDTO == null)
            throw new IllegalArgumentException("Category Request cannot be null");

        Category category = new Category();
        category.setName(requestDTO.name());
        return category;
    }
}
