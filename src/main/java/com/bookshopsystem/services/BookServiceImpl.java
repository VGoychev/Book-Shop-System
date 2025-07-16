package com.bookshopsystem.services;

import com.bookshopsystem.dto.BookInputDto;
import com.bookshopsystem.dto.BookRelationsDto;
import com.bookshopsystem.entities.Book;
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
}
