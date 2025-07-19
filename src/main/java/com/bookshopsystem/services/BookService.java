package com.bookshopsystem.services;

import com.bookshopsystem.dto.BookInputDto;
import com.bookshopsystem.dto.BookRelationsDto;
import com.bookshopsystem.dto.BookSummaryDto;
import com.bookshopsystem.entities.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {
    Book create(BookInputDto bookInputDto, BookRelationsDto bookRelationsDto);
    List<Book> findReleasedAfter(int year);
    List<Book> findByAuthor(String firstName, String lastName);
    List<Book> findAllByAgeRestriction(String ageRestriction);

    List<Book> findAllGoldenBooks();

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(double lower, double upper);

    List<Book> findAllNotReleasedInYear(int year);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDate);

    List<Book> findAllTitlesContaining(String word);

    List<Book> findAllByAuthorLastNameStartingWith(String suffix);

    List<Book> findAllByTitleLength(int length);

    List<Book> findTotalCopiesByAuthorName(String firstName, String lastName);

    BookSummaryDto findBookByTitle(String title);
}
