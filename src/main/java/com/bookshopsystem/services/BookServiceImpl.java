package com.bookshopsystem.services;

import com.bookshopsystem.dto.BookInputDto;
import com.bookshopsystem.dto.BookRelationsDto;
import com.bookshopsystem.dto.BookSummaryDto;
import com.bookshopsystem.entities.Book;
import com.bookshopsystem.enums.AgeRestriction;
import com.bookshopsystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book create(BookInputDto bookInputDto, BookRelationsDto bookRelationsDto) {
        Book book = new Book();
        book.setAgeRestriction(bookInputDto.getAgeRestriction());
        book.setReleaseDate(bookInputDto.getReleaseDate());
        book.setCopies(bookInputDto.getCopies());
        book.setPrice(bookInputDto.getPrice());
        book.setEditionType(bookInputDto.getEditionType());
        book.setTitle(bookInputDto.getTitle());
        book.setAuthor(bookRelationsDto.getAuthor());
        book.setCategory(bookRelationsDto.getCategory());
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findReleasedAfter(int year) {
        LocalDate releaseDate = LocalDate.of(year, 1, 1);
        return bookRepository.findAllByReleaseDateGreaterThanEqual(releaseDate);
    }

    @Override
    public List<Book> findByAuthor(String firstName, String lastName) {
        return bookRepository.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(firstName, lastName);
    }

    @Override
    public List<Book> findAllByAgeRestriction(String ageRestriction) {
        return bookRepository.findAllByAgeRestriction(AgeRestriction.valueOf(ageRestriction.toUpperCase()));
    }

    @Override
    public List<Book> findAllGoldenBooks() {
        return bookRepository.findAllGoldenBooks();
    }

    @Override
    public List<Book> findAllByPriceLessThanOrPriceGreaterThan(double lower, double upper) {
        return bookRepository.findAllByPriceLessThanOrPriceGreaterThan(lower, upper);
    }

    @Override
    public List<Book> findAllNotReleasedInYear(int year) {
        LocalDate start = LocalDate.of(year, 1, 1);
        LocalDate end = LocalDate.of(year, 12, 31);
        return bookRepository.findAllNotReleasedInYear(start, end);
    }

    @Override
    public List<Book> findAllByReleaseDateBefore(LocalDate releaseDate) {
        return bookRepository.findAllByReleaseDateBefore(releaseDate);
    }

    @Override
    public List<Book> findAllTitlesContaining(String word) {
        return bookRepository.findAllByTitleContaining(word);
    }

    @Override
    public List<Book> findAllByAuthorLastNameStartingWith(String suffix) {
        return bookRepository.findAllByAuthorLastNameStartingWith(suffix);
    }

    @Override
    public List<Book> findAllByTitleLength(int length) {
        return bookRepository.findAllByTitleLengthLongerThan(length);
    }
    @Override
    public List<Book> findTotalCopiesByAuthorName(String firstName, String lastName) {
        return bookRepository.findAllByAuthorFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public BookSummaryDto findBookByTitle(String title) {
        Book book = bookRepository.findBookByTitle(title);
        BookSummaryDto bookSummary = new BookSummaryDto(book.getTitle(), book.getEditionType(), book.getAgeRestriction(), book.getPrice());
        return bookSummary;
    }
}
