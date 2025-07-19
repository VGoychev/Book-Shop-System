package com.bookshopsystem.repositories;

import com.bookshopsystem.dto.BookSummaryDto;
import com.bookshopsystem.entities.Book;
import com.bookshopsystem.enums.AgeRestriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByReleaseDateGreaterThanEqual(LocalDate releaseDate);

    List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String authorFirstName, String authorLastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    @Query("SELECT b FROM Book AS b WHERE b.copies < 5000")
    List<Book> findAllGoldenBooks();

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(double lower, double upper);

    @Query("SELECT b FROM Book AS b WHERE b.releaseDate < :start OR b.releaseDate > :end")
    List<Book> findAllNotReleasedInYear(LocalDate start, LocalDate end);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDate);

    @Query("SELECT b FROM Book AS b WHERE b.title = :title")
    Book findBookByTitle(@Param("title") String title);

    List<Book> findAllByTitleContaining(String word);

    List<Book> findAllByAuthorLastNameStartingWith(String suffix);

    @Query("SELECT b FROM Book AS b WHERE LENGTH(b.title) > :length")
    List<Book> findAllByTitleLengthLongerThan(int length);

    @Query("SELECT b FROM Book AS b WHERE b.author.firstName = :firstName AND b.author.lastName = :lastName")
    List<Book> findAllByAuthorFirstNameAndLastName(String firstName, String lastName);
}
