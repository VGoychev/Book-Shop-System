package com.bookshopsystem.entities;

import com.bookshopsystem.enums.AgeRestriction;
import com.bookshopsystem.enums.EditionType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "age_restriction")
    private AgeRestriction ageRestriction;

    @Column(name = "copies")
    private int copies;

    @Column(name = "description")
    private String description;

    @Column(name = "edition_type")
    private EditionType editionType;

    @Column(name = "price")
    private double price;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "title")
    private String title;

    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    private Author author;

    @ManyToOne(targetEntity = Category.class)
    @JoinTable(name = "books_categories",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Category category;

    public Integer getBookId() {
        return bookId;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
        if(author != null){
            author.getBooks().add(this);
        }
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
