package com.example.librarymanager.repository;

import com.example.librarymanager.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    @Query("select b from Book b where  b.price > ?1 and b.author = ?2 ")
    Optional<List<Book>> findAllBooksWithLowerPriceAndAuthor(double price , String author);
}
