package org.blogplatform.backend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.blogplatform.backend.domain.dtos.CategoryDto;
import org.blogplatform.backend.domain.dtos.CreateCategoryRequest;
import org.blogplatform.backend.domain.entities.Category;
import org.blogplatform.backend.mappers.CategoryMapperImpl;
import org.blogplatform.backend.service.impl.CategoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl categoryService;
    private final CategoryMapperImpl categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
        List<CategoryDto> categories = categoryService.listCategories()
            .stream()
            .map(categoryMapper::toDto)
            .toList();

        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
        @Valid @RequestBody CreateCategoryRequest request
        ) {

        Category categoryToCreate = categoryMapper.toEntity(request);
        Category savedCategory = categoryService.createCategory(categoryToCreate);

        return new ResponseEntity<>(
            categoryMapper.toDto(savedCategory),
            HttpStatus.CREATED
        );

    }
}
