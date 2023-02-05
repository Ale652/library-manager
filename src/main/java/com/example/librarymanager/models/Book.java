package com.example.librarymanager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
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

}
