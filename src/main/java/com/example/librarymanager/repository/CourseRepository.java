package com.example.librarymanager.repository;

import com.example.librarymanager.models.Course;
import org.springframework.data.domain.Page;
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

    @Query(value = "select * from course where id in(select course_id from\n" +
            "                         (select course_id, max(mycount)\n" +
            "                         from (select course_id , count(course_id) mycount\n" +
            "                               from enrolment\n" +
            "                               group by  course_id) as result) as resultfinal\n" +
            "                         ) ;", nativeQuery = true)
    Optional<List<Course>> getMostPopularCourse();

    // TODO : is not working with pageable, in SQL is working
    /*
    *select * from course where id in(select course_id from
                            (select course_id, mycount
                             from (select course_id , count(course_id) mycount
                                      from enrolment
                                  group by  course_id
                                      order by mycount desc) as result) as resultfinal);
    * */
    @Query(value = "select * from course where id in(select course_id from\n" +
            "                            (select course_id, mycount\n" +
            "                             from (select course_id , count(course_id) mycount\n" +
        "                                       from enrolment\n" +
        "                                       group by  course_id\n" +
        "                                       order by mycount desc) as result) as resultfinal\n" +
        "                                       ) as finalRez;", nativeQuery = true)
    Page<Course> getMostPopularCourses(Pageable pageable);


}
