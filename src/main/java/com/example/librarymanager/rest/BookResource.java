package com.example.librarymanager.rest;

import com.example.librarymanager.dto.BookListResponse;
import com.example.librarymanager.dto.StudentListResponse;
import com.example.librarymanager.dto.createEntities.CreateBookInDBRequest;
import com.example.librarymanager.models.Book;
import com.example.librarymanager.models.Student;
import com.example.librarymanager.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @GetMapping("/booksListInAscendingOrderByPrice")
    public ResponseEntity<BookListResponse> getAllBooksInAscendingOrderByPrice(){

        BookListResponse bookListResponse = BookListResponse.builder()
                .bookList(bookService.getAllBooksInAscendingOrderByPrice())
                .message("All books in ascending order by price")
                .build();

        return new ResponseEntity<>(bookListResponse, HttpStatus.OK);
    }

    @GetMapping("/booksListInDescendingOrderByStars")
    public ResponseEntity<BookListResponse> getAllBooksInDescendingOrderByStars(){

        BookListResponse bookListResponse = BookListResponse.builder()
                .bookList(bookService.getAllBooksInDescendingOrderByStars())
                .message("All books in desceding order by stars")
                .build();

        return new ResponseEntity<>(bookListResponse, HttpStatus.OK);
    }

    @GetMapping("/booksListByAuthorLike")
    public ResponseEntity<BookListResponse> findByAuthorLike(@RequestParam String author){

        BookListResponse bookListResponse = BookListResponse.builder()
                .bookList(bookService.findByAuthorLike(author))
                .message("All books of author")
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

    @GetMapping("/bookListGratherCreatedAtThanMentionedOne")
    public ResponseEntity<BookListResponse> bookListByCreated_atGreaterThan(@RequestParam LocalDate date){

        BookListResponse bookListResponse = BookListResponse.builder()
                .bookList(bookService.findBookByCreatedAtGreaterThan(date))
                .message("All books after mentioned date")
                .build();

        return new ResponseEntity<>(bookListResponse, HttpStatus.OK);
    }

    @GetMapping("/bookTopByOrderByPriceAsc")
    public ResponseEntity<BookListResponse> bookTopByOrderByPriceAsc(){
        BookListResponse mostExpensiveBook = BookListResponse.builder()
                .bookList(bookService.findTopByOrderByPriceAsc().get())
                .message("Most expensive books")
                .build();

        return new ResponseEntity<>(mostExpensiveBook, HttpStatus.OK);
    }

    @GetMapping("/bookTopByOrderByPriceDesc")
    public ResponseEntity<BookListResponse> bookTopByOrderByPriceDesc(){
        BookListResponse mostCheapsBook = BookListResponse.builder()
                .bookList(bookService.findTopByOrderByPriceDesc().get())
                .message("Most cheap books")
                .build();

        return new ResponseEntity<>(mostCheapsBook, HttpStatus.OK);
    }

    @GetMapping("/findLower10PriceBooks")
    public ResponseEntity<BookListResponse> booksListLower10PriceBooks(){
        BookListResponse booksListLower10PriceBooks = BookListResponse.builder()
                .bookList(bookService.findLower10PriceBooks().stream().toList())
                .message("Most cheap 10 books")
                .build();

        return new ResponseEntity<>(booksListLower10PriceBooks, HttpStatus.OK);
    }

    @GetMapping("/bestBooks")
    public ResponseEntity<BookListResponse> bestBooks(){
        BookListResponse bestBooks = BookListResponse.builder()
                .bookList(bookService.bestBooks().get())
                .message("Best 10 books by stars")
                .build();

        return new ResponseEntity<>(bestBooks, HttpStatus.OK);
    }

    @GetMapping("/findAllByStudent")
    public ResponseEntity<BookListResponse> findAllByStudent(@RequestParam String student_email ){
        BookListResponse bookListResponse = BookListResponse.builder()
                .bookList(bookService.findAllByStudent_Email(student_email).get())
                .message("Get all books of a student.")
                .build();

        return new ResponseEntity<>(bookListResponse, HttpStatus.OK);
    }

    @PostMapping("/createBookInDB")
    public ResponseEntity<Book> createBookInDB(@RequestBody CreateBookInDBRequest createBookInDBRequest){
        Book book = Book.builder()
                .id(createBookInDBRequest.getId())
                .title(createBookInDBRequest.getTitle())
                .author(createBookInDBRequest.getAuthor())
                .price(createBookInDBRequest.getPrice())
                .stars(createBookInDBRequest.getStars())
                .createdAt(createBookInDBRequest.getCreatedAt())
                .build();

        BookListResponse bookListResponse = BookListResponse.builder()
                .bookList(Stream.of(bookService.createBookInDB(book)).collect(Collectors.toList()))
                .message("A new book created")
                .build();

                return new ResponseEntity(bookListResponse, HttpStatus.OK);
    }
}
