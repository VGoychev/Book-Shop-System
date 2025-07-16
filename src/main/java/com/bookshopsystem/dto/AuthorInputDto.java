package com.bookshopsystem.dto;

public class AuthorInputDto {
    private final String firstName;
    private final String lastName;

    public AuthorInputDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
}
