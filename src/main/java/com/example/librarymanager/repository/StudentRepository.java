package com.example.librarymanager.repository;

import com.example.librarymanager.models.Book;
import com.example.librarymanager.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

//    @Query("select max(count(s.books)) from Student s")
//    Optional<List<Student>> findMaxBooksOfStudent();


    @Query("select s from Student s where  s.age >25 ")
    Optional<List<Student>> findAllStudenstAgedMoreThan25();


    @Query("select s from Student s where s.age = (select  min(s.age) from  Student  a)")
    Optional<List<Student>> findLowerAgeStudent();

    @Query("select s from Student s where s.age = (select  max(s.age) from  Student  a)")
    Optional<List<Student>> findHigherAgeStudent();

//    @Query("select s from Student s where s.books.size = (select max(s1.books.size) from Student s1)")
//    Page<List<Student>> selectStudentWithMaxBooks(Pageable pageable);


    @Query("select s from Student s order by s.age asc")
    Page<Student> findLower10AgedStudent(Pageable pageable);
}
