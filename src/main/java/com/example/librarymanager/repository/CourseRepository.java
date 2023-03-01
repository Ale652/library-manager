package com.example.librarymanager.repository;

import com.example.librarymanager.models.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c order by c.department asc")
    Optional<List<Course>> getFirst10CoursesOrderByepartmentAsc(Pageable p);

    Course findByNameLike(String course);

    List<Course> findByDepartmentLike(String course);


//    // get list of enrolments for a Course
//    @Query("select c.enrolments from Course c where c.id = ?1")
//    Optional<List<Enrolment>> getListOfEnrolmentsForACourse(Long course_id);
}
