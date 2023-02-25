package com.example.librarymanager;

import com.example.librarymanager.dto.AddEnrollementRequest;
import com.example.librarymanager.dto.RemoveEnrollmentRequest;
import com.example.librarymanager.models.Book;
import com.example.librarymanager.models.Course;
import com.example.librarymanager.models.Enrolment;
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
import java.time.LocalDateTime;


@SpringBootApplication
public class LibraryManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagerApplication.class, args);
    }


    @Bean
    CommandLineRunner asdasdsa(BookRepository bookRepository, StudentRepository studentRepository
                                , CourseRepository courseRepository, StudentService studentService) {
        return  args->{

            // Book Queries

//            bookRepository.findAll().forEach(e->{
//
//                System.out.println(e);
//            });

//            bookRepository.findAllBooksGraterPriceThanMentionedOfSpecificAuthor(15,"Daryle Kelbie")
//                    .get().forEach(System.out::println);

//            System.out.println("############### findLowerPriceBook :");
//            System.out.println(bookRepository.findLowerPriceBook().get());
//            System.out.println("############### findMaxPriceBook :");
//            System.out.println(bookRepository.findMaxPriceBook().get());
//            System.out.println("############### findTop10PriceBooks :");
//            bookRepository.findTop10PriceBooks(PageRequest.of(1,10)).forEach(System.out::println);
//            System.out.println("############### findLower10PriceBooks :");
//            bookRepository.findLower10PriceBooks(PageRequest.of(1, 10)).forEach(System.out::println);
//            System.out.println("############### Best Books :");
//            bookRepository.bestBooks().get().forEach(System.out::println);


//            Student student = studentRepository.findById(1L).get();
//            Course course = courseRepository.findById(1L).get();



//            for(int i=0;i<10;i++){
//
//                student.addBook(
//                        Book.builder().author("sad"+i).price(12).title("asdasdas").stars(12l).build()
//                );
//            }
//
//            studentRepository.saveAndFlush(student);

            // Student queries

//            student.getBooks().forEach(System.out::println);
            // 1. get student with most books
//            studentRepository.findMaxBooksOfStudent().get().forEach(System.out::println);

            // 2. findAllStudenstAgedMoreThan25
//            System.out.println("findAllStudenstAgedMoreThan25");
//            studentRepository.findAllStudenstAgedMoreThan25().get().forEach(System.out::println);
//
//            // 3. findLowerAgeStudent
//            System.out.println("findLowerAgeStudent");
//            studentRepository.findLowerAgeStudent().get().forEach(System.out::println);
//
//            // 4. findHigherAgeStudent
//            System.out.println("findHigherAgeStudent");
//            studentRepository.findHigherAgeStudent().get().forEach(System.out::println);
//
//            // 5. selectStudentWithMaxBooks
//            System.out.println("selectStudentWithMaxBooks");
//            studentRepository.selectStudentWithMaxBooks(PageRequest.of(1, 1)).forEach(System.out::println);
//
//            // 6. findLower10AgedStudent
//            System.out.println("findLower10AgedStudent");
//            studentRepository.findLower10AgedStudent(PageRequest.of(1, 10)).forEach(System.out::println);



            // Course Queries

            // 1. getFirst10CoursesOrderByepartmentAsc
//            System.out.println("getFirst10CoursesOrderByepartmentAsc: ");
//            courseRepository.getFirst10CoursesOrderByepartmentAsc(PageRequest.of(1, 10))
//                    .get().forEach(System.out::println);
//
//
//            Enrolment build = Enrolment.builder().
//                    created_at(LocalDateTime.of(2023, 6, 30, 12, 00)).build();
//            student.addEnrolment(build);
//            course.addEnrolment(build);
//
//
//            studentRepository.saveAndFlush(student);
////          courseRepository.saveAndFlush(course);
//
//            System.out.println("Get list of enrolments for course_id : ");
//            courseRepository.getListOfEnrolmentsForACourse(course.getId()).get().
//                    forEach(System.out::println);
//
//            System.out.println("selectStudentWithMaxBooks");
//            studentRepository.selectStudentWithMaxBooks()
//                    .get().forEach(System.out::println);

//            System.out.println("getAllStudents \n");
//            studentService.getAllStudents().forEach(System.out::println);

//            AddEnrollementRequest addEnrollementRequest = new AddEnrollementRequest(1, 2);
//            studentService.enrolStudentToCourse(addEnrollementRequest);

            RemoveEnrollmentRequest removeEnrollmentRequest = new RemoveEnrollmentRequest(3, 2);
            studentService.unenrollStudentFromCourse(removeEnrollmentRequest);
        };
    }


}
