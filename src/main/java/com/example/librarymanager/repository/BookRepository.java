package com.example.librarymanager.repository;

import com.example.librarymanager.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    @Query("select b from Book b where  b.price > ?1 and b.author = ?2 ")
    Optional<List<Book>> findAllBooksGraterPriceThanMentionedOfSpecificAuthor(double price , String author);

    @Query("select  b from Book b where  b.price = (select  min(a.price) from  Book  a)")
    Optional<Book> findLowerPriceBook();
    //
    @Query("select b from Book b where b.price =  (select max(a.price) from  Book a )")
    Optional<Book> findMaxPriceBook();

    @Query("select b from Book b order by b.price desc")

    Page<Book> findTop10PriceBooks(Pageable pageable);

    @Query("select b from Book b order by b.price asc")
    Page<Book> findLower10PriceBooks(Pageable pageable);

    @Query("select b from Book b where b.stars = (select max(a.stars) from Book a)")
    Optional<List<Book>> bestBooks();


}
