package se.lexicon.ecommerceplatform.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.ecommerceplatform.dto.request.ProductRequestDTO;
import se.lexicon.ecommerceplatform.dto.response.ProductResponseDTO;
import se.lexicon.ecommerceplatform.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductRequestDTO requestDTO) {

        ProductResponseDTO responseDTO = productService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {

        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<ProductResponseDTO>> findByName(@RequestParam @NotBlank String name) {

        return ResponseEntity.ok(productService.searchByName(name));
    }
}
