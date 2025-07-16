package com.bookshopsystem.services;

import com.bookshopsystem.dto.BookInputDto;
import com.bookshopsystem.dto.BookRelationsDto;
import com.bookshopsystem.entities.Book;

import java.util.List;

public interface BookService {
    Book create(BookInputDto bookInputDto, BookRelationsDto bookRelationsDto);
    List<Book> findReleasedAfter(int year);
    List<Book> findByAuthor(String firstName, String lastName);
}
