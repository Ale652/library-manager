package com.example.librarymanager;

import com.example.librarymanager.models.Exemplu;
import com.example.librarymanager.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


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

            bookRepository.findAllBooksWithLowerPriceAndAuthor(15.8709393,"Maryanne Say")
                    .get().forEach(System.out::println);

        };
    }
}
