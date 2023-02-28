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
import com.example.librarymanager.services.BookService;
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
                                , CourseRepository courseRepository, StudentService studentService,
                               BookService bookService) {
        return  args->{

            //TODO: ORDONATE + Change Structure in order to be able to execute whatever you want - not only a functionarlity
            // Book Queries


            // Student

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



            // Course

            // 1. getFirst10CoursesOrderByepartmentAsc
//            System.out.println("getFirst10CoursesOrderByepartmentAsc: ");
//            courseRepository.getFirst10CoursesOrderByepartmentAsc(PageRequest.of(1, 10))
//                    .get().forEach(System.out::println);
//


            // Remove Enrolment
            Course course = courseRepository.findById(1L).get();
            Student stuent = studentRepository.findById(1L).get();

//            studentService.removeEnrolment(stuent, course);

//            studentService.addEnrolment(stuent, course);

//            AddEnrollementRequest addEnrollementRequest = new AddEnrollementRequest(1, 2);
//            studentService.addEnrolment(addEnrollementRequest);

//            RemoveEnrollmentRequest removeEnrollmentRequest = new RemoveEnrollmentRequest(1, 2);
//            studentService.removeEnrolment(removeEnrollmentRequest);


            // Book

//            List<Book> listOfBooksInAscendingOrderingPrice = bookService.getAllBooksInAscendingOrderByPrice();
//            System.out.println(listOfBooksInAscendingOrderingPrice);

//            List<Book> listOfBooksInDescendingOrderByStars = bookService.getAllBooksInDescendingOrderByStars();
//            System.out.println(listOfBooksInDescendingOrderByStars);

            String likePattern = "Jeddy Christaeas";
//            List<Book> listOfBooksByAuthor = bookService.findByAuthorLike(likePattern);
//            System.out.println(listOfBooksByAuthor);

//            List<Book> listOfBooksByPriceGreaterThanAndAuthorLike =
//                    bookService.findByPriceGreaterThanAndAuthorLike(50L, likePattern);
//            System.out.println(listOfBooksByPriceGreaterThanAndAuthorLike);


//            System.out.println(bookService.findTopByOrderByPriceAsc());

//            System.out.println(bookService.findTopByOrderByPriceDesc());

//            bookService.findTop10PriceBooks().forEach(System.out::println);

//            bookService.findLower10PriceBooks().forEach(System.out::println);

            bookService.bestBooks().get().forEach(System.out::println);



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

        };
    }




}
