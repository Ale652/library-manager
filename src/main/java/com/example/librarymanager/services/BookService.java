package com.example.librarymanager.services;

import com.example.librarymanager.exceptions.EmptyDatabaseExeception;
import com.example.librarymanager.models.Book;
import com.example.librarymanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
        @Autowired
        BookRepository bookRepository;

        public List<Book> getAllBooks(){

                List<Book> books = bookRepository.findAll();

                if(books.size()==0){

                        throw  new EmptyDatabaseExeception("Dtabase empty ");
                }

                return  books;
        }

        //findAllBooksGraterPriceThanMentionedOfSpecificAuthor
        public List<Book> getAllBooksGraterPriceThanMentionedOfSpecificAuthor(double price , String author) {
                Optional<List<Book>> books = bookRepository.findAllBooksGraterPriceThanMentionedOfSpecificAuthor(price, author);
                if(books.get().size()==0){

                        throw  new EmptyDatabaseExeception("Dtabase empty ");
                }

                return  books.get();
        }
}