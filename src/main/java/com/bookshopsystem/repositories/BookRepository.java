package com.bookshopsystem.repositories;

import com.bookshopsystem.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByReleaseDateGreaterThanEqual(LocalDate releaseDate);

    List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String authorFirstName, String authorLastName);
}
