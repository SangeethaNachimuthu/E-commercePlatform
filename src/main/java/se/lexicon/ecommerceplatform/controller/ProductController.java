package se.lexicon.ecommerceplatform.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.ecommerceplatform.dto.request.ProductRequestDTO;
import se.lexicon.ecommerceplatform.dto.response.ProductResponseDTO;
import se.lexicon.ecommerceplatform.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Validated
@Tag(name = "Product Controller", description = "APIs for managing products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Operation(summary = "Create a new product")
    @Tag(name = "Product Operations")
    public ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductRequestDTO requestDTO) {

        ProductResponseDTO responseDTO = productService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    @Operation(summary = "List all products")
    @Tag(name = "Product Operations")
    public ResponseEntity<List<ProductResponseDTO>> findAll() {

        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/search/name")
    @Operation(summary = "Find a product by name")
    @Tag(name = "Product Operations")
    public ResponseEntity<List<ProductResponseDTO>> findByName(@RequestParam @NotBlank String name) {

        return ResponseEntity.ok(productService.searchByName(name));
    }
}
