package se.lexicon.ecommerceplatform.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.ecommerceplatform.dto.request.ProductRequestDTO;
import se.lexicon.ecommerceplatform.dto.response.CategoryResponseDTO;
import se.lexicon.ecommerceplatform.dto.response.ProductResponseDTO;
import se.lexicon.ecommerceplatform.entity.Category;
import se.lexicon.ecommerceplatform.entity.Product;

@Component
public class ProductMapper {

    public ProductResponseDTO toResponse(Product product) {

        if (product == null)
            throw new IllegalArgumentException("Product cannot be null");

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory().getName()
        );
    }

    public Product toProductEntity(ProductRequestDTO productRequestDTO) {

        if (productRequestDTO == null)
            throw new IllegalArgumentException("Product Request cannot be null");

//        Category category = new Category();
//        category.setId(productRequestDTO.categoryId());

        Product product = new Product();
        product.setName(productRequestDTO.name());
        product.setPrice(productRequestDTO.price());
      //  product.setCategory(category);

        return product;
    }

    public CategoryResponseDTO toCategoryResponse(Category category) {

        if (category == null)
            throw new IllegalArgumentException("Category cannot be null");

        return new CategoryResponseDTO(
                category.getId(),
                category.getName()
        );
    }
}
