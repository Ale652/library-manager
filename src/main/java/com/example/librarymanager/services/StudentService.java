package com.example.librarymanager.services;

import com.example.librarymanager.dto.AddEnrollementRequest;
import com.example.librarymanager.exceptions.CourseNotPresentException;
import com.example.librarymanager.exceptions.EmptyDatabaseExeception;
import com.example.librarymanager.exceptions.StudentAlreadyEnrolledException;
import com.example.librarymanager.exceptions.StudentNotPresentException;
import com.example.librarymanager.models.Course;
import com.example.librarymanager.models.Enrolment;
import com.example.librarymanager.models.Student;
import com.example.librarymanager.repository.CourseRepository;
import com.example.librarymanager.repository.EnrollementRepository;
import com.example.librarymanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    private CourseRepository courseRepository;


    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, EnrollementRepository enrollementRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Student> getAllStudents() {

        List<Student> students = studentRepository.findAll();

        if (students.size() == 0) {

            throw new EmptyDatabaseExeception("Dtabase empty ");
        }

        return students;

    }

    public String getStudentNameWithMaxBooks() {
        Optional<List<String>> studentWithMaxBooks = studentRepository.selectStudentWithMaxBooks();

        if (studentWithMaxBooks.isEmpty()) {

            throw new EmptyDatabaseExeception("No student with max books.");
        }
        return studentWithMaxBooks.get().stream().map(s -> s).collect(Collectors.joining());
    }

    public List<String> getListOfStudentNameWithMaxBooks() {
        Optional<List<String>> studentWithMaxBooks = studentRepository.selectStudentWithMaxBooks();

        if (studentWithMaxBooks.isEmpty()) {

            throw new EmptyDatabaseExeception("No student with max books.");
        }
        return studentWithMaxBooks.get();
    }

    public List<String> findHigherAgeStudent() {
        Optional<List<String>> studentWithHighestAge = studentRepository.findHigherAgeStudent();

        if (studentWithHighestAge.isEmpty()) {

            throw new EmptyDatabaseExeception("No student with max age");
        }
        return studentWithHighestAge.get();
    }

    public List<String> findLowestAgeStudent() {
        Optional<List<String>> studentWithLowestAge = studentRepository.findLowerAgeStudent();

        if (studentWithLowestAge.isEmpty()) {

            throw new EmptyDatabaseExeception("No student with max age");
        }
        return studentWithLowestAge.get();
    }


    @Transactional
    @Modifying
    public void enrolStudentToCourse(AddEnrollementRequest addEnrollementRequest) {
        //tratam prima oara logica pentru exceptii
        // vf existenta studentului
        Optional<Student> student = studentRepository.findById(addEnrollementRequest.getIdStudent().longValue());
        if (student.isEmpty()) {
            throw new StudentNotPresentException("This student is not preset !");
        }

        // vf existenta cursului
        Optional<Course> course = courseRepository.findById(addEnrollementRequest.getIdCourse().longValue());

        if (course.isEmpty()) {
            throw new CourseNotPresentException("This ocurse is not present !");
        }



        Enrolment enrolment = new Enrolment(student.get(), course.get());

        //todo:vf daca studentu este deja inrolat la curs

        // NOT RECOMMENDED AS BELOW : WE SHOULD AVOID TO INTERROGATE THE DATABSE IF POSSIBLE TO GET DATA FROM WHAT WE HAVE ALREADY LOADED
//        Optional<Enrolment> enrolment = enrollementRepository.findEnrolment(student.get().getId(), course.get().getId());

//        for(Enrolment enrolment : student.get().getEnrolments()){
//            if(enrolment.getCourse().getId() == course.get().getId())
//                throw new StudentAlreadyEnrolledException("This student is already enrolled to this course !");
//        }

        if (student.get().getEnrolments().contains(enrolment)) {
            throw new StudentAlreadyEnrolledException("This student is already enrolled to this course !");
        }
        Student student1 = student.get();
        student1.addEnrolment(enrolment);
        studentRepository.saveAndFlush(student1);
    }


}
