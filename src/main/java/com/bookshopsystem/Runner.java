package com.bookshopsystem;

import com.bookshopsystem.dto.AuthorInputDto;
import com.bookshopsystem.dto.BookInputDto;
import com.bookshopsystem.dto.BookRelationsDto;
import com.bookshopsystem.dto.CategoryInputDto;
import com.bookshopsystem.entities.Author;
import com.bookshopsystem.entities.Book;
import com.bookshopsystem.entities.Category;
import com.bookshopsystem.enums.AgeRestriction;
import com.bookshopsystem.enums.EditionType;
import com.bookshopsystem.services.AuthorService;
import com.bookshopsystem.services.BookService;
import com.bookshopsystem.services.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class Runner implements CommandLineRunner {
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    public Runner(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<String> categories = readSeedFile("categories.txt");
        List<Category> categoriesList = new ArrayList<>();
        for(String category : categories){
            if(!category.isEmpty()){
            CategoryInputDto categoryInputDto = new CategoryInputDto(category);
                Category currentCategory = categoryService.create(categoryInputDto);
                categoriesList.add(currentCategory);
            }
        }
        List<String> authors = readSeedFile("authors.txt");
        List<Author> authorsList = new ArrayList<>();
        for(String author : authors){
            String[] split = author.split("\\s+");
            String firstName = split[0];
            String lastName = split[1];
            AuthorInputDto authorInputDto = new AuthorInputDto(firstName, lastName);
            Author currentAuthor = authorService.create(authorInputDto);
            authorsList.add(currentAuthor);
        }

        List<String> books = readSeedFile("books.txt");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

        for(String book : books){
            String[] bookInfo = book.split("\\s+", 6);
            EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
            LocalDate releaseDate = LocalDate.parse(bookInfo[1], formatter);
            int copies = Integer.parseInt(bookInfo[2]);
            double price = Double.parseDouble(bookInfo[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(bookInfo[4])];
            String title = bookInfo[5];
            int randomAuthorIndex = ThreadLocalRandom.current().nextInt(0, authorsList.size());
            Author author = authorsList.get(randomAuthorIndex);
            int randomCategoryIndex = ThreadLocalRandom.current().nextInt(0, categoriesList.size());
            Category category = categoriesList.get(randomCategoryIndex);

            BookInputDto bookInputDto = new BookInputDto(ageRestriction, releaseDate, copies, price, editionType, title);
            BookRelationsDto bookRelationsDto = new BookRelationsDto(author, category);
            bookService.create(bookInputDto, bookRelationsDto);
        }

        List<Book> booksList = bookService.findReleasedAfter(2000);
        for(Book book : booksList){
            System.out.println(book.getTitle());
        }
        System.out.println("------------------------------------------------------------");
        List<Author> activeAuthors = authorService.findActiveBefore(1990);
        for(Author author : activeAuthors){
            System.out.println(author.getFirstName() + " " + author.getLastName());
        }
        System.out.println("------------------------------------------------------------");
        authorService.getSummary().forEach(a -> System.out.printf("%s %s - %d books%n", a.getFirstName(), a.getLastName(), a.getBooksCount()));
        System.out.println("------------------------------------------------------------");
        List<Book> booksByAuthor = bookService.findByAuthor("George", "Powell");
        for(Book book : booksByAuthor){
            System.out.printf("%s (%s) - %d%n", book.getTitle(), book.getReleaseDate(), book.getCopies());

        }
    }

    private List<String> readSeedFile(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        InputStream inputStream = resource.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);


        return reader.lines().toList();
    }
}
