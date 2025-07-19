package com.bookshopsystem.repositories;

import com.bookshopsystem.dto.AuthorSummaryDto;
import com.bookshopsystem.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{
    List<Author> findAllByBooksReleaseDateLessThan(LocalDate releaseDate);

    @Query("SELECT a.firstName, a.lastName, size(a.books) FROM Author a ORDER BY SIZE(a.books) DESC")
    List<AuthorSummaryDto> getSummary();

    List<Author> findAllByFirstNameEndsWith(String suffix);
}
