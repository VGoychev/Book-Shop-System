package com.bookshopsystem.services;

import com.bookshopsystem.dto.CategoryInputDto;
import com.bookshopsystem.entities.Category;
import com.bookshopsystem.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(CategoryInputDto categoryInputDto) {
        Category category = new Category();
        category.setName(categoryInputDto.getName());

        return categoryRepository.save(category);
    }
}
