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
            

            // Book

//            List<Book> listOfBooksInAscendingOrderingPrice = bookService.getAllBooksInAscendingOrderByPrice();
//            System.out.println(listOfBooksInAscendingOrderingPrice);

//            List<Book> listOfBooksInDescendingOrderByStars = bookService.getAllBooksInDescendingOrderByStars();
//            System.out.println(listOfBooksInDescendingOrderByStars);

//            String likePattern = "Jeddy Christaeas";
//            List<Book> listOfBooksByAuthor = bookService.findByAuthorLike(likePattern);
//            System.out.println(listOfBooksByAuthor);

//            List<Book> listOfBooksByPriceGreaterThanAndAuthorLike =
//                    bookService.findByPriceGreaterThanAndAuthorLike(50L, likePattern);
//            System.out.println(listOfBooksByPriceGreaterThanAndAuthorLike);


//            System.out.println(bookService.findTopByOrderByPriceAsc());

//            System.out.println(bookService.findTopByOrderByPriceDesc());

//            bookService.findTop10PriceBooks().forEach(System.out::println);

//            bookService.findLower10PriceBooks().forEach(System.out::println);

//            bookService.bestBooks().get().forEach(System.out::println);



            // Student

//            student.getBooks().forEach(System.out::println);

//            studentRepository.findMaxBooksOfStudent().get().forEach(System.out::println);

//            studentService.findByAgeGreaterThan(25).forEach(System.out::println);
//
//            studentService.findByAgeLessThan(25).forEach(System.out::println);

//            studentService.findTopByOrderByAgeAsc().forEach(System.out::println);

//            studentService.findTopByOrderByAgeDesc().forEach(System.out::println);

//            System.out.println("selectStudentWithMaxBooks");
//            studentRepository.selectStudentWithMaxBooks(PageRequest.of(1, 1)).forEach(System.out::println);
//
//            studentRepository.findTop10ByOrderByAgeDesc().get().forEach(System.out::println);


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


            //----------------------------------------------------------------------------------

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
