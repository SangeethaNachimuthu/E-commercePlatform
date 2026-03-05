package se.lexicon.ecommerceplatform.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.ecommerceplatform.dto.request.ProductRequestDTO;
import se.lexicon.ecommerceplatform.dto.response.ProductResponseDTO;
import se.lexicon.ecommerceplatform.entity.Category;
import se.lexicon.ecommerceplatform.entity.Product;
import se.lexicon.ecommerceplatform.exception.ResourceNotFoundException;
import se.lexicon.ecommerceplatform.mapper.ProductMapper;
import se.lexicon.ecommerceplatform.repository.CategoryRepository;
import se.lexicon.ecommerceplatform.repository.ProductRepository;
import se.lexicon.ecommerceplatform.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              ProductMapper mapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ProductResponseDTO create(ProductRequestDTO requestDTO) {

        if (requestDTO == null) {
            throw new IllegalArgumentException("ProductRequestDTO cannot be null");
        }

        Category category = categoryRepository.findById(requestDTO.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with ID: " + requestDTO.categoryId()));

        Product product = mapper.toProductEntity(requestDTO);
        product.setCategory(category);

        Product createdProduct = productRepository.save(product);

        return mapper.toResponse(createdProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> findAll() {

        List<Product> products = productRepository.findAll();
        return products
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<ProductResponseDTO> searchByName(String name) {

        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        List<Product> productList = productRepository.findByNameContainingIgnoreCase(name);

        return productList
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
