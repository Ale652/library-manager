package com.example.librarymanager.services;

import com.example.librarymanager.exceptions.EmptyDatabaseExeception;
import com.example.librarymanager.models.Book;
import com.example.librarymanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

        public Optional<Book> findTopByOrderByPriceAsc(){
                return bookRepository.findTopByOrderByPriceAsc();
        }

        public Optional<Book> findTopByOrderByPriceDesc(){
                return bookRepository.findTopByOrderByPriceDesc();
        }

//        public  Page<Book>  findTop10PriceBooks(){
//                return bookRepository.findTop10PriceBooks(PageRequest.of(1,10));
//        }

        public  Page<Book>  findLower10PriceBooks(){ return bookRepository.findLower10PriceBooks(PageRequest.of(1, 10));}

        public Optional<List<Book>> bestBooks(){
                return bookRepository.bestBooks();
        }

}