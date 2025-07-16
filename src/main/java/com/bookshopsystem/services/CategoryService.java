package com.bookshopsystem.services;

import com.bookshopsystem.dto.CategoryInputDto;
import com.bookshopsystem.entities.Category;

public interface CategoryService {
    Category create(CategoryInputDto categoryInputDto);
}
