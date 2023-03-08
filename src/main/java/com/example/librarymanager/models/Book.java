package com.example.librarymanager.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Objects;

@Table(name="book")
@Entity(name="Book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Book {
    @Id
    @SequenceGenerator(
            name="book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "books_sequence"
    )
    private Long id;

    @Column(
            name="title",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @Size(min = 2, message = "min title length should be 2")
    String title;

    @Size(min = 2, message = "min author length should be 2")
    @Column( name="author",
            nullable = false)
    String author;

    @Column( name="price",
            nullable = false)
            @DecimalMin(value = "0.1",message = "Price should have a value.")
    double price;

    @Column( name="stars")
    Long stars;

    @Column( name ="created_at")
    LocalDate createdAt;

    @ManyToOne
    @JoinColumn(
            name="user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name="user_id_fk")

    )
    @JsonBackReference
    private Student student;

    public Book(String title, String author, double price, Long stars, LocalDate createdAt) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.stars = stars;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Double.compare(book.price, price) == 0 && Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(stars, book.stars) && Objects.equals(createdAt, book.createdAt) && Objects.equals(student, book.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, price, stars, createdAt, student);
    }
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", stars=" + stars +
                '}';
    }
}
