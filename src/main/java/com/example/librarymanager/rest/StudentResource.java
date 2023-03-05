package com.example.librarymanager.rest;

import com.example.librarymanager.dto.*;
import com.example.librarymanager.services.StudentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

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

    //TODO : to test it
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
}
