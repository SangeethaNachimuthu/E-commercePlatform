package se.lexicon.ecommerceplatform.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.ecommerceplatform.dto.request.CategoryRequestDTO;
import se.lexicon.ecommerceplatform.dto.response.CategoryResponseDTO;
import se.lexicon.ecommerceplatform.entity.Category;
import se.lexicon.ecommerceplatform.mapper.CategoryMapper;
import se.lexicon.ecommerceplatform.mapper.ProductMapper;
import se.lexicon.ecommerceplatform.repository.CategoryRepository;
import se.lexicon.ecommerceplatform.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> findAll() {

        List<Category> categories = categoryRepository.findAll();

        return categories
                .stream()
                .map(mapper::toCategoryResponse)
                .toList();
    }

    @Override
    public CategoryResponseDTO create(CategoryRequestDTO requestDTO) {

        if (requestDTO == null) {
            throw new IllegalArgumentException("CategoryRequestDTO cannot be null");
        }

        Category category = mapper.toCategoryEntity(requestDTO);
        Category createdCategory = categoryRepository.save(category);

        return mapper.toCategoryResponse(createdCategory);
    }
}
