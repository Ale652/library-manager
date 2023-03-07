package com.example.librarymanager.repository;

import com.example.librarymanager.models.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<List<Course>> findTop10ByOrderByNameAsc();

    Optional<List<Course>> findTop10ByOrderByNameDesc();

    Optional<List<Course>> findTop10ByOrderByDepartmentAsc();

    Optional<List<Course>> findTop10ByOrderByDepartmentDesc();

    Optional<List<Course>> findByNameLike(String course);

    Optional<List<Course>> findByNameEquals(String course);

    Optional<List<Course>>  findByDepartmentLike(String course);

    Optional<List<Course>> findAllByStudents_Email(String emailStudent);

    Optional<List<Course>> findByDepartmentEquals(String course);

    @Query("select c from Course c order by c.department asc")
    Optional<List<Course>> getFirst10CoursesOrderByepartmentAsc(Pageable p);

}
