package com.bookshopsystem.dto;

public class CategoryInputDto {
    private final String name;

    public CategoryInputDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
