package com.example.librarymanager.dto;

import com.example.librarymanager.models.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class BookListResponse {
    private String message;
    private List<Book> bookList;
}
