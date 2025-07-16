package com.bookshopsystem.dto;

import com.bookshopsystem.enums.AgeRestriction;
import com.bookshopsystem.enums.EditionType;

import java.time.LocalDate;

public class BookInputDto {
    private final AgeRestriction ageRestriction;
    private final LocalDate releaseDate;
    private final int copies;
    private final double price;
    private final EditionType editionType;
    private final String title;

    public BookInputDto(AgeRestriction ageRestriction, LocalDate releaseDate,
                        int copies, double price, EditionType editionType, String title) {
        this.ageRestriction = ageRestriction;
        this.releaseDate = releaseDate;
        this.copies = copies;
        this.price = price;
        this.editionType = editionType;
        this.title = title;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    public int getCopies() {
        return copies;
    }
    public double getPrice() {
        return price;
    }
    public EditionType getEditionType() {
        return editionType;
    }
    public String getTitle() {
        return title;
    }

}
