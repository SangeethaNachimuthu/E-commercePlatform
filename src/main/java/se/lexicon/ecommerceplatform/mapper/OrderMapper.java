package se.lexicon.ecommerceplatform.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.ecommerceplatform.dto.request.OrderItemRequestDTO;
import se.lexicon.ecommerceplatform.dto.request.OrderRequestDTO;
import se.lexicon.ecommerceplatform.dto.response.OrderItemResponseDTO;
import se.lexicon.ecommerceplatform.dto.response.OrderResponseDTO;
import se.lexicon.ecommerceplatform.entity.Customer;
import se.lexicon.ecommerceplatform.entity.Order;
import se.lexicon.ecommerceplatform.entity.OrderItem;

import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderItemResponseDTO toOrderItemResponse(OrderItem orderItem) {

        if (orderItem == null) throw new IllegalArgumentException("Order Item cannot be null");

        return new OrderItemResponseDTO(
                orderItem.getProduct().getId(),
                orderItem.getQuantity(),
                orderItem.getPriceAtPurchase()
        );
    }

    public OrderItem toOrderItemEntity(OrderItemRequestDTO orderItemRequestDTO) {

        if (orderItemRequestDTO == null)
            throw new IllegalArgumentException("Order Item Request cannot be null");

        OrderItem orderItem = new OrderItem();

        orderItem.setId(orderItemRequestDTO.productId());
        orderItem.setQuantity(orderItemRequestDTO.quantity());

        return orderItem;
    }


    public OrderResponseDTO toOrderResponse(Order order) {

        if (order == null) throw new IllegalArgumentException("Order cannot be null");

        return new OrderResponseDTO(
                order.getId(),
                order.getOrderDate(),
                order.getStatus().toString(),
                order.getItems()
                        .stream()
                        .map(this::toOrderItemResponse)
                        .collect(Collectors.toList())
        );
    }

    public Order toOrderEntity(OrderRequestDTO orderRequestDTO) {

        if (orderRequestDTO == null)
            throw new IllegalArgumentException("Order Request cannot be null");

        Order order = new Order();

        Customer customer = new Customer();
        customer.setId(orderRequestDTO.customerId());

        order.setCustomer(customer);

        return order;
    }
}
