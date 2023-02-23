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


    @Query("select concat(s.firstName,' ',s.secondName,' ',s.age) from Student s where s.age = (select  min(a.age) from  Student  a)")
    Optional<List<String>> findLowerAgeStudent();

    @Query("select concat(s.firstName,' ',s.secondName,' ',s.age) from Student s where s.age = (select  max(a.age) from  Student  a)")
    Optional<List<String>> findHigherAgeStudent();

    @Query(value = "select first_name ,count(*) as number from student " +
            "join book on book.user_id = student.id " +
            "group by first_name  order by  number desc  limit 1  "
            , nativeQuery = true)
    Optional<List<String>> selectStudentWithMaxBooks();


    @Query("select s from Student s order by s.age asc")
    Page<Student> findLower10AgedStudent(Pageable pageable);

}
