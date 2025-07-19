package com.bookshopsystem.dto;

import com.bookshopsystem.enums.AgeRestriction;
import com.bookshopsystem.enums.EditionType;

public class BookSummaryDto {
    private final String title;
    private final EditionType editionType;
    private final AgeRestriction ageRestriction;
    private final double price;

    public BookSummaryDto(String title, EditionType editionType, AgeRestriction ageRestriction, double price) {
        this.title = title;
        this.editionType = editionType;
        this.ageRestriction = ageRestriction;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }
    public EditionType getEditionType() {
        return editionType;
    }
    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }
    public double getPrice() {
        return price;
    }
}
