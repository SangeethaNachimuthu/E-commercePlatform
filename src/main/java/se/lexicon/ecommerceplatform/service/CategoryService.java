package se.lexicon.ecommerceplatform.service;

import se.lexicon.ecommerceplatform.dto.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryResponseDTO> findAll();
}
