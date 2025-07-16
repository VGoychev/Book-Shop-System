package com.bookshopsystem.dto;

public class AuthorSummaryDto {
    private final String firstName, lastName;
    private final int booksCount;

    public AuthorSummaryDto(String firstName, String lastName, int booksCount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.booksCount = booksCount;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getBooksCount() {
        return booksCount;
    }
}
