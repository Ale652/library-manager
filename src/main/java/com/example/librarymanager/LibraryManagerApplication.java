package com.example.librarymanager;

import com.example.librarymanager.dto.AddEnrollementRequest;
import com.example.librarymanager.dto.EnrollementInterface;
import com.example.librarymanager.dto.RemoveEnrollmentRequest;
import com.example.librarymanager.exceptions.CourseNotPresentException;
import com.example.librarymanager.exceptions.StudentAlreadyEnrolledException;
import com.example.librarymanager.exceptions.StudentNotPresentException;
import com.example.librarymanager.models.Book;
import com.example.librarymanager.models.Course;
import com.example.librarymanager.models.Student;
import com.example.librarymanager.repository.BookRepository;
import com.example.librarymanager.repository.CourseRepository;
import com.example.librarymanager.repository.StudentRepository;
import com.example.librarymanager.services.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@SpringBootApplication
public class LibraryManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagerApplication.class, args);
    }


    @Bean
    CommandLineRunner asdasdsa(BookRepository bookRepository, StudentRepository studentRepository
                                , CourseRepository courseRepository, StudentService studentService) {
        return  args->{

            Course course = courseRepository.findById(1L).get();
            Student stuent = studentRepository.findById(1L).get();

           studentService.removeEnrolment(stuent, course);


//            stuent.removeCourse(course);

            studentRepository.saveAndFlush(stuent);

        };
    }




}
