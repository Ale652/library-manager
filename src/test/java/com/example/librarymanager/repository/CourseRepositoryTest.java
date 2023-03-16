package com.example.librarymanager.repository;
import com.example.librarymanager.LibraryManagerApplication;
import com.example.librarymanager.models.Course;
import com.example.librarymanager.models.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LibraryManagerApplication.class)
public class CourseRepositoryTest {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;


    @BeforeEach
    void cleanup(){

        courseRepository.deleteAll();
    }

    // TODO : Issue : Many to many constraint on Student is moved from ALL to MERGE in order to not get exception
    @Test
    @Transactional
    public void check_most_popular_course(){
        // given
        Course course1 = Course.builder()
                .id(1L)
                .department("department1")
                .name("name1")
                .build();

        Course course2 = Course.builder()
                .id(2L)
                .department("department2")
                .name("name2")
                .build();

        Student student1= Student.builder()
                .age(0)
                .email("tes1@gmail.com")
                .firstName("tess")
                .secondName("ceva")
                .enrolledCourses(List.of(course1, course2))
                .build();

        Student student2= Student.builder()
                .age(0)
                .email("test2@gmail.com")
                .firstName("tess")
                .secondName("ceva")
                .enrolledCourses(List.of(course1))
                .build();

        Student student3= Student.builder()
                .age(0)
                .email("test3@gmail.com")
                .firstName("tess")
                .secondName("ceva")
                .enrolledCourses(List.of(course1))
                .build();

        // when
        List<Course> initialCourses = new ArrayList<>(List.of(course1, course2));
        List<Student> initialStudents = new ArrayList<>(List.of(student1,student2,student3));
        studentRepository.saveAllAndFlush(initialStudents);
        courseRepository.saveAll(initialCourses);

        // then
        assertEquals(course1, courseRepository.getMostPopularCourse().get().get(0));

    }



}
