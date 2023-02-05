package com.example.librarymanager;

import com.example.librarymanager.models.Book;
import com.example.librarymanager.models.Exemplu;
import com.example.librarymanager.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


@SpringBootApplication
public class LibraryManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagerApplication.class, args);
    }


    @Bean
    CommandLineRunner comand(BookRepository bookRepository, Exemplu exemplu) {
        return  args->{
//            bookRepository.findAll().forEach(e->{
//
//                System.out.println(e);
//            });

//            bookRepository.findAllBooksWithLowerPriceAndAuthor(15.8709393,"Maryanne Say")
//                    .get().forEach(System.out::println);

            System.out.println("############### findLowerPriceBook :");
            System.out.println(bookRepository.findLowerPriceBook().get());
            System.out.println("############### findMaxPriceBook :");
            System.out.println(bookRepository.findMaxPriceBook().get());
            System.out.println("############### findTop10PriceBooks :");
            bookRepository.findTop10PriceBooks(PageRequest.of(1,10)).forEach(System.out::println);
            System.out.println("############### findLower10PriceBooks :");
            bookRepository.findLower10PriceBooks(PageRequest.of(1, 10)).forEach(System.out::println);
            System.out.println("############### Best Books :");
            bookRepository.bestBooks().get().forEach(System.out::println);

        };
    }


}
