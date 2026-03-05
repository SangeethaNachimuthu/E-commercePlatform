package se.lexicon.ecommerceplatform.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.ecommerceplatform.dto.request.CustomerRequestDTO;
import se.lexicon.ecommerceplatform.dto.response.CustomerResponseDTO;
import se.lexicon.ecommerceplatform.entity.Address;
import se.lexicon.ecommerceplatform.entity.Customer;
import se.lexicon.ecommerceplatform.exception.DuplicateEntryException;
import se.lexicon.ecommerceplatform.exception.ResourceNotFoundException;
import se.lexicon.ecommerceplatform.mapper.CustomerMapper;
import se.lexicon.ecommerceplatform.repository.CustomerRepository;
import se.lexicon.ecommerceplatform.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public CustomerResponseDTO register(CustomerRequestDTO requestDTO) {

        if (requestDTO == null) {
            throw new IllegalArgumentException("CustomerRequestDTO cannot be null");
        }
        if (requestDTO.email() == null ) {
            throw new IllegalArgumentException("Email cannot be null");
        }

        if (customerRepository.existsByEmailIgnoreCase(requestDTO.email())) {
            throw new DuplicateEntryException("Customer with email already exists");
        }

        Customer customer = mapper.toCustomerEntity(requestDTO);
        Customer savedCustomer = customerRepository.save(customer);

        return mapper.toResponse(savedCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDTO findById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));

        return mapper.toResponse(customer);
    }


    @Override
    @Transactional
    public CustomerResponseDTO update(Long id, CustomerRequestDTO requestDTO) {

        if (id == null || requestDTO == null) {
            throw new IllegalArgumentException("Id or CustomerRequestDTO cannot be null");
        }

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));

        customer.setFirstName(requestDTO.firstName());
        customer.setLastName(requestDTO.lastName());
        customer.setEmail(requestDTO.email());

        Address address = customer.getAddress();

        address.setStreet(requestDTO.street());
        address.setCity(requestDTO.city());
        address.setZipcode(requestDTO.zipcode());

        Customer updatedCustomer = customerRepository.save(customer);

        return mapper.toResponse(updatedCustomer);
    }
}
