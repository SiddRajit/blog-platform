package org.blogplatform.backend.service;

import org.blogplatform.backend.domain.entities.Category;

import java.util.List;

public interface CategoryService {

    List<Category> listCategories();
    Category createCategory(Category category);
}
