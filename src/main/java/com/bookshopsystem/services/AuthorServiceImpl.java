package com.bookshopsystem.services;

import com.bookshopsystem.dto.AuthorInputDto;
import com.bookshopsystem.dto.AuthorSummaryDto;
import com.bookshopsystem.entities.Author;
import com.bookshopsystem.repositories.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author create(AuthorInputDto authorInputDto) {
        Author author = new Author();
        author.setFirstName(authorInputDto.getFirstName());
        author.setLastName(authorInputDto.getLastName());
        return authorRepository.save(author);
    }

    @Override
    public List<Author> findActiveBefore(int year) {
        LocalDate releaseDate = LocalDate.of(year, 1, 1);
        return authorRepository.findAllByBooksReleaseDateLessThan(releaseDate);
    }

    @Override
    public List<AuthorSummaryDto> getSummary() {
        return authorRepository.getSummary();
    }
}
