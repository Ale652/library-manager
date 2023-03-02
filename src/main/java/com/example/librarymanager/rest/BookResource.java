package com.example.librarymanager.rest;

import com.example.librarymanager.dto.BookListResponse;
import com.example.librarymanager.dto.StudentListResponse;
import com.example.librarymanager.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/books")
public class BookResource {

    private BookService bookService;
    public BookResource(BookService bookService){ this.bookService = bookService; }

    @GetMapping("/")
    public ResponseEntity<BookListResponse> bookList(){

        BookListResponse bookListResponse = BookListResponse.builder()
                .bookList(bookService.getAllBooks())
                .message("All books")
                .build();

        return new ResponseEntity<>(bookListResponse, HttpStatus.OK);
    }

    @GetMapping("/bookListGraterPriceThanMentionedOfSpecificAuthor")
    public ResponseEntity<BookListResponse> bookListGraterPriceThanMentionedOfSpecificAuthor(@RequestParam long price, @RequestParam String author){

        BookListResponse bookListResponse = BookListResponse.builder()
                .bookList(bookService.findByPriceGreaterThanAndAuthorLike(price, author))
                .message("All books")
                .build();

        return new ResponseEntity<>(bookListResponse, HttpStatus.OK);
    }
}
