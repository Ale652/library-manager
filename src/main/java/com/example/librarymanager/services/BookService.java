package com.example.librarymanager.services;

import com.example.librarymanager.exceptions.EmptyDatabaseExeception;
import com.example.librarymanager.models.Book;
import com.example.librarymanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

        BookRepository bookRepository;

        public BookService(BookRepository bookRepository){
                this.bookRepository = bookRepository;
        }

        public List<Book> getAllBooks(){

                List<Book> books = bookRepository.findAll();

                if(books.size()==0){

                        throw  new EmptyDatabaseExeception("Dtabase empty ");
                }

                return  books;
        }

        public List<Book> getAllBooksInAscendingOrderByPrice(){

                List<Book> books = bookRepository.findAllByOrderByPriceAsc();

                if(books.size()==0){

                        throw  new EmptyDatabaseExeception("Dtabase empty ");
                }

                return  books;
        }

        public List<Book> getAllBooksInDescendingOrderByStars(){

                List<Book> books = bookRepository.findAllByOrderByStarsDesc();

                if(books.size()==0){

                        throw  new EmptyDatabaseExeception("Dtabase empty ");
                }

                return  books;
        }

        public List<Book> findByAuthorLike(String likePattern){
                return bookRepository.findByAuthorLike(likePattern);
        }

        public List<Book> findByPriceGreaterThanAndAuthorLike(Long price, String likePattern){
                return bookRepository.findByPriceGreaterThanAndAuthorLike( price, likePattern);
        }

        public List<Book> findBookByCreatedAtGreaterThan(LocalDate date){ return bookRepository.findBookByCreatedAtGreaterThan(date); }

        public Optional<List<Book>> findTopByOrderByPriceAsc(){
                return bookRepository.findTopByOrderByPriceAsc();
        }

        public Optional<List<Book>> findTopByOrderByPriceDesc(){
                return bookRepository.findTopByOrderByPriceDesc();
        }

        public  Page<Book>  findLower10PriceBooks(){ return bookRepository.findLower10PriceBooks(PageRequest.of(1, 10));}

        public Optional<List<Book>> bestBooks(){
                return bookRepository.bestBooks();
        }

        public Optional<List<Book>> findAllByStudent_Email(String email){ return bookRepository.findAllByStudent_Email(email); }

}