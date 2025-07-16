package com.bookshopsystem.dto;

import com.bookshopsystem.entities.Author;
import com.bookshopsystem.entities.Category;

import java.util.List;

public class BookRelationsDto {
    private final Author author;
    private final Category category;

    public BookRelationsDto(Author author, Category category) {
        this.author = author;
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }
    public Category getCategory() {
        return category;
    }
}
