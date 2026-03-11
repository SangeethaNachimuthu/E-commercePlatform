package se.lexicon.ecommerceplatform.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.ecommerceplatform.dto.request.CustomerRequestDTO;
import se.lexicon.ecommerceplatform.dto.response.CustomerResponseDTO;
import se.lexicon.ecommerceplatform.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@Validated
@Tag(name = "Customer Controller", description = "APIs for managing customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }

    @PostMapping
    @Operation(summary = "Create a new customer")
    @Tag(name = "Customer Operations")
    public ResponseEntity<CustomerResponseDTO> create(@Valid @RequestBody CustomerRequestDTO requestDTO) {

        CustomerResponseDTO responseDTO = customerService.register(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a customer by id")
    @Tag(name = "Customer Operations")
    public ResponseEntity<CustomerResponseDTO> findById(@PathVariable @Positive Long id) {

        CustomerResponseDTO responseDTO = customerService.findById(id);
        if (responseDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a customer by id")
    @Tag(name = "Customer Operations")
    public ResponseEntity<CustomerResponseDTO> update(@PathVariable @Positive Long id,
                                                      @Valid @RequestBody CustomerRequestDTO requestDTO) {

        CustomerResponseDTO responseDTO = customerService.update(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}