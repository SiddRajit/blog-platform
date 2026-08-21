package org.blogplatform.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.blogplatform.backend.domain.entities.Category;
import org.blogplatform.backend.repositories.CategoryRepository;
import org.blogplatform.backend.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        Category category = new Category();
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {

        if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new IllegalArgumentException("Category already exists with name" + category.getName());
        }

        return categoryRepository.save(category);
    }
}
