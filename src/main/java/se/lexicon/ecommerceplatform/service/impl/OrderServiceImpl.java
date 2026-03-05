package se.lexicon.ecommerceplatform.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.ecommerceplatform.dto.request.OrderItemRequestDTO;
import se.lexicon.ecommerceplatform.dto.request.OrderRequestDTO;
import se.lexicon.ecommerceplatform.dto.response.OrderResponseDTO;
import se.lexicon.ecommerceplatform.entity.*;
import se.lexicon.ecommerceplatform.exception.ResourceNotFoundException;
import se.lexicon.ecommerceplatform.mapper.OrderMapper;
import se.lexicon.ecommerceplatform.repository.CustomerRepository;
import se.lexicon.ecommerceplatform.repository.OrderRepository;
import se.lexicon.ecommerceplatform.repository.ProductRepository;
import se.lexicon.ecommerceplatform.service.OrderService;

import java.time.Instant;

@Service
public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;

    public OrderServiceImpl(CustomerRepository customerRepository,
                            ProductRepository productRepository,
                            OrderRepository orderRepository,
                            OrderMapper mapper) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public OrderResponseDTO placeOrder(OrderRequestDTO requestDTO) {

        if (requestDTO == null) {
            throw new IllegalArgumentException("OrderRequestDTO cannot be null");
        }

        Customer customer = customerRepository.findById(requestDTO.customerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with ID: " + requestDTO.customerId()));

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(Instant.now());
        order.setStatus(OrderStatus.CREATED);

        for (OrderItemRequestDTO itemRequestDTO : requestDTO.orderItems()) {

            Product product = productRepository.findById(itemRequestDTO.productId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Product not found with ID: " + itemRequestDTO.productId()));

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequestDTO.quantity());
            orderItem.setPriceAtPurchase(product.getPrice());
            orderItem.setOrder(order);

            order.addItems(orderItem);
        }

        Order savedOrder = orderRepository.save(order);
        return mapper.toOrderResponse(savedOrder);
    }
}
