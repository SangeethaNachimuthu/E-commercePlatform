package se.lexicon.ecommerceplatform.service;

import se.lexicon.ecommerceplatform.dto.request.CustomerRequestDTO;
import se.lexicon.ecommerceplatform.dto.response.CustomerResponseDTO;

public interface CustomerService {

    CustomerResponseDTO register(CustomerRequestDTO requestDTO);
    CustomerResponseDTO findById(Long id);
    CustomerResponseDTO update(Long id, CustomerRequestDTO requestDTO);
}
