package se.lexicon.ecommerceplatform.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.ecommerceplatform.dto.request.CustomerRequestDTO;
import se.lexicon.ecommerceplatform.dto.response.AddressResponseDTO;
import se.lexicon.ecommerceplatform.dto.response.CustomerResponseDTO;
import se.lexicon.ecommerceplatform.entity.Address;
import se.lexicon.ecommerceplatform.entity.Customer;

@Component
public class CustomerMapper {

    public CustomerResponseDTO toResponse(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException("Customer can't be null");
        }
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getCreatedAt(),
                new AddressResponseDTO(
                        customer.getAddress().getId(),
                        customer.getAddress().getStreet(),
                        customer.getAddress().getCity(),
                        customer.getAddress().getZipcode())
        );
    }

    public Customer toCustomerEntity(CustomerRequestDTO customerRequestDTO) {
        if (customerRequestDTO == null)
            throw new IllegalArgumentException("Customer Request can't be null");

        Address address = new Address();
        address.setStreet(customerRequestDTO.street());
        address.setCity(customerRequestDTO.city());
        address.setZipcode(customerRequestDTO.zipcode());

        Customer customer = new Customer();
        customer.setFirstName(customerRequestDTO.firstName());
        customer.setLastName(customerRequestDTO.lastName());
        customer.setEmail(customerRequestDTO.email());
        customer.setAddress(address);

        return customer;
    }
}
