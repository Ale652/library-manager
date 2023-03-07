package com.example.librarymanager.repository;

import com.example.librarymanager.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public List<Book> findAllByOrderByStarsDesc();

    public List<Book> findByAuthorLike(String likePattern);

    Optional<List<Book>> findTopByOrderByPriceAsc();

    Optional<List<Book>> findTopByOrderByPriceDesc();

    public List<Book> findAllByOrderByPriceAsc();

    public List<Book> findAllByOrderByPriceDesc();

    Optional<List<Book>> findAllByStudent_Email(String student_email);

   List<Book> findByPriceGreaterThanAndAuthorLike(Long price, String likePattern);

    public List<Book> findBookByCreatedAtGreaterThan(LocalDate date);

    @Query("select b from Book b order by b.price asc")
    Page<Book> findLower10PriceBooks(Pageable pageable);

    @Query("select b from Book b where b.stars = (select max(a.stars) from Book a)")
    Optional<List<Book>> bestBooks();


}
