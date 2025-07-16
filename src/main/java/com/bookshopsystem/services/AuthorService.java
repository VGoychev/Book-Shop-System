package com.bookshopsystem.services;

import com.bookshopsystem.dto.AuthorInputDto;
import com.bookshopsystem.dto.AuthorSummaryDto;
import com.bookshopsystem.entities.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {
    Author create(AuthorInputDto authorInputDto);

    List<Author> findActiveBefore(int year);
    List<AuthorSummaryDto> getSummary();
}
