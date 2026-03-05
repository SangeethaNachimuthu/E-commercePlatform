package se.lexicon.ecommerceplatform.service;

import se.lexicon.ecommerceplatform.dto.request.OrderRequestDTO;
import se.lexicon.ecommerceplatform.dto.response.OrderResponseDTO;

public interface OrderService {

    OrderResponseDTO placeOrder(OrderRequestDTO requestDTO);
}
