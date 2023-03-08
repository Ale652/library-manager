package com.example.librarymanager.dto.createEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class CreateBookInDBRequest {
    Long id;
    String title;
    String author;
    double price;
    Long stars;
    LocalDate createdAt;
}
