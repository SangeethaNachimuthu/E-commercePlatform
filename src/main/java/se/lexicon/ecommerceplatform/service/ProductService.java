package se.lexicon.ecommerceplatform.service;

import se.lexicon.ecommerceplatform.dto.request.ProductRequestDTO;
import se.lexicon.ecommerceplatform.dto.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO create(ProductRequestDTO requestDTO);
    List<ProductResponseDTO> findAll();
    List<ProductResponseDTO> searchByName(String name);
}
