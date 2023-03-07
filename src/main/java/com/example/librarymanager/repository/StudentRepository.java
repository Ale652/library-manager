package com.example.librarymanager.repository;

import com.example.librarymanager.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByAgeGreaterThan(Integer age);

    List<Student> findByAgeLessThan(Integer age);

    List<Optional<Student>> findTopByOrderByAgeAsc();

    List<Optional<Student>> findTopByOrderByAgeDesc();

    Optional<List<Student>> findTop10ByOrderByAgeDesc();

    Optional<List<Student>> findTop10ByOrderByAgeAsc();

    List<Student> findByEmailLike(String likePattern);

    Optional<List<Student>> findAllByBooks_TitleLike(String title);

    Optional<List<Student>> findAllByEnrolledCourses_NameLike(String courseName);

    List<Student> findByFirstNameLikeAndSecondNameLike(String firstName, String secondName);

    @Query(value = "select first_name ,count(*) as number from student " +
            "join book on book.user_id = student.id " +
            "group by first_name  order by  number desc  limit 1  "
            , nativeQuery = true)
    Optional<List<String>> selectStudentsWithMaxBooks();

}
