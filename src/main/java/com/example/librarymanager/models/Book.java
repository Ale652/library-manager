package com.example.librarymanager.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="book")
@Entity(name="Book")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    String title;

    @Column( name="author",
            nullable = false)
    String author;

    @Column( name="price",
            nullable = false)
    double price;

    @Column( name="stars")
    Long stars;

}
