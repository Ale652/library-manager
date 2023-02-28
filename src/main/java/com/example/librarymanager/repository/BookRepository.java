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

    public List<Book> findAllByOrderByStarsDesc();

    public List<Book> findByAuthorLike(String likePattern);

    Optional<Book> findTopByOrderByPriceAsc();

    Optional<Book> findTopByOrderByPriceDesc();

    public List<Book> findAllByOrderByPriceAsc();

    List<Book> findByPriceGreaterThanAndAuthorLike(Long price, String likePattern);
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
