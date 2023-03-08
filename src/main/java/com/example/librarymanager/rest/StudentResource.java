package com.example.librarymanager.rest;

import com.example.librarymanager.dto.*;
import com.example.librarymanager.services.StudentService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/v1/stundents")
@Slf4j
public class StudentResource {

    private StudentService studentService;
    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public ResponseEntity<StudentListResponse>studentList(){

        StudentListResponse studentListResponse=StudentListResponse.builder()
                .studentList(studentService.getAllStudents())
                .message("All stundents")
                .build();

        return new ResponseEntity<>(studentListResponse, HttpStatus.OK);
    }

    @GetMapping("/listOfStudentsWithMaxBooks")
    public ResponseEntity<StudentListWithMaxNrBooksResponse>listOfStudentsWithMaxBooks(){
        StudentListWithMaxNrBooksResponse studentListWithMaxNrBooksResponse = StudentListWithMaxNrBooksResponse.builder()
                .listOfStudentsWithMaxNrBooks(studentService.getListOfStudentNameWithMaxBooks())
                .message("This are is the student with max books list")
                .build();

        return  new ResponseEntity<>(studentListWithMaxNrBooksResponse, HttpStatus.OK);
    }

    @GetMapping("/listOfStudentsWithHighestAge")
    public ResponseEntity<StudentsWithHighestAgeResponse>listOfStudentsWithHighestAge(){
        StudentsWithHighestAgeResponse studentListWithHighestAgeResponse = StudentsWithHighestAgeResponse.builder()
                .listOfStudentsWithHighestAge((studentService.findTopByOrderByAgeDesc().
                        stream().map(e -> e.get().getFirstName())).
                        collect(Collectors.toList()))
                .message("These are the students with higher age ")
                .build();

        return  new ResponseEntity<>(studentListWithHighestAgeResponse, HttpStatus.OK);
    }

    @GetMapping("/listOfStudentsWithLowestAge")
    public ResponseEntity<StudentsWithLowestAgeResponse>listOfStudentsWithLowestAge(){
        StudentsWithLowestAgeResponse studentsWithLowestAgeResponse = StudentsWithLowestAgeResponse.builder()
                .listOfStudentsWithLowestAge(studentService.findTopByOrderByAgeAsc().
                        stream().map(e->e.get().getFirstName()).
                        collect(Collectors.toList()))
                .message("These are the students with higher age ")
                .build();

        return  new ResponseEntity<>(studentsWithLowestAgeResponse, HttpStatus.OK);
    }

    @GetMapping("/findAllByBooks_TitleLike")
    public ResponseEntity<StudentListResponse>findAllByBooks_TitleLike(@RequestParam String title){
        StudentListResponse studentListResponse = StudentListResponse.builder()
                .studentList(studentService.findAllByBooks_TitleLike(title).get())
                .message("Find all Students that have mentioned Book Name")
                .build();

        return new ResponseEntity(studentListResponse, HttpStatus.OK);

    }

    @GetMapping("/findAllByEnrolledCourses_NameLike")
    public ResponseEntity<StudentListResponse>findAllByEnrolledCourses_NameLike(@RequestParam String courseName){
        StudentListResponse studentListResponse = StudentListResponse.builder()
                .studentList(studentService.findAllByEnrolledCourses_NameLike(courseName).get())
                .message("Get all students enrolled to the course name")
                .build();

        return new ResponseEntity(studentListResponse, HttpStatus.OK);
    }

    @GetMapping("/findByAgeGreaterThan")
    public ResponseEntity<StudentListResponse> findByAgeGreaterThan(@RequestParam Integer age){
        StudentListResponse studentListResponse = StudentListResponse.builder()
                .studentList(studentService.findByAgeGreaterThan(age))
                .message("Get Students with Age Grather than " + age)
                .build();

        return new ResponseEntity<>(studentListResponse, HttpStatus.OK);
    }

    @GetMapping("/findByAgeLessThan")
    public ResponseEntity<StudentListResponse> findByAgeLessThan(@RequestParam Integer age){
        StudentListResponse studentListResponse = StudentListResponse.builder()
                .studentList(studentService.findByAgeLessThan(age))
                .message("Get Students with Age Grather than " + age)
                .build();

        return new ResponseEntity<>(studentListResponse, HttpStatus.OK);
    }

    @GetMapping("/findTop10ByOrderByAgeDesc")
    public ResponseEntity<StudentsWithLowestAgeResponse>findTop10ByOrderByAgeDesc(){
        StudentsWithLowestAgeResponse studentsWithLowestAgeResponse = StudentsWithLowestAgeResponse.builder()
                .listOfStudentsWithLowestAge(studentService.findTop10ByOrderByAgeDesc().get()
                        .stream().map(e -> e.getFirstName())
                        .collect(Collectors.toList()))
                .message("These are 10 students with higher age ")
                .build();

        return  new ResponseEntity<>(studentsWithLowestAgeResponse, HttpStatus.OK);
    }

    @GetMapping("/findTop10ByOrderByAgeAsc")
    public ResponseEntity<StudentsWithLowestAgeResponse>findTop10ByOrderByAgeAsc(){
        StudentsWithLowestAgeResponse studentsWithLowestAgeResponse = StudentsWithLowestAgeResponse.builder()
                .listOfStudentsWithLowestAge(studentService.findTop10ByOrderByAgeAsc().get()
                        .stream().map(e -> e.getFirstName())
                        .collect(Collectors.toList()))
                .message("These are 10 students with higher age ")
                .build();

        return  new ResponseEntity<>(studentsWithLowestAgeResponse, HttpStatus.OK);
    }

    @GetMapping("/findByEmailLike")
    public ResponseEntity<StudentListResponse>findByEmailLike(@RequestParam String email){
        StudentListResponse studentListResponse = StudentListResponse.builder()
                .studentList(studentService.findByEmailLike(email))
                .message("Find Student by email address")
                .build();

        return new ResponseEntity<>(studentListResponse, HttpStatus.OK);
    }

    @GetMapping("/findByFirstNameLikeAndSecondNameLike")
    public ResponseEntity<StudentListResponse>findByFirstNameLikeAndSecondNameLike(@RequestParam String firstName, @RequestParam String secondName){
        StudentListResponse studentListResponse = StudentListResponse.builder()
                .studentList(studentService.findByFirstNameLikeAndSecondNameLike(firstName, secondName))
                .message("Find Student by firstName and secondName")
                .build();

        return new ResponseEntity<>(studentListResponse, HttpStatus.OK);
    }

    @PostMapping("/addEnrollment")
    public ResponseEntity addEnrollment(@Valid @RequestBody AddEnrollementRequest addEnrollementRequest){

        log.info("REST request to enroll {}",addEnrollementRequest);

        studentService.addEnrolment(addEnrollementRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/removeEnrollment")
    public ResponseEntity removeEnrollment(@Valid @RequestBody RemoveEnrollmentRequest removeEnrollmentRequest){

        log.info("REST request to unenroll {}", removeEnrollmentRequest);

        studentService.removeEnrolment(removeEnrollmentRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/addBookToStudent")
    public ResponseEntity addBookToStudent(@RequestBody AddBookToStudentRequest addBookToStudentRequest){
        BookListResponse bookListResponse = BookListResponse.builder()
                .bookList(Stream.of(studentService.addBookToStudent(addBookToStudentRequest)).collect(Collectors.toList()))
                .message("Added book to Student")
                .build();

        return new ResponseEntity(bookListResponse, HttpStatus.OK);
    }

    @PostMapping("/removeBookFromStudent")
    public ResponseEntity removeBookFromStudent(@RequestBody RemoveBookFromStudentRequest removeBookFromStudentRequest){
        BookListResponse bookListResponse = BookListResponse.builder()
                .bookList(Stream.of(studentService.removeBookFromStudent(removeBookFromStudentRequest)).collect(Collectors.toList()))
                .message("Removed book from Student")
                .build();

        return new ResponseEntity(bookListResponse, HttpStatus.OK);
    }
}
