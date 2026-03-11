package se.lexicon.ecommerceplatform.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.ecommerceplatform.dto.request.CategoryRequestDTO;
import se.lexicon.ecommerceplatform.dto.response.CategoryResponseDTO;
import se.lexicon.ecommerceplatform.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@Valid @RequestBody CategoryRequestDTO requestDTO) {

        CategoryResponseDTO responseDTO = categoryService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {

        return ResponseEntity.ok(categoryService.findAll());
    }
}
