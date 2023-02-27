package com.example.librarymanager.services;

import com.example.librarymanager.dto.AddEnrollementRequest;
import com.example.librarymanager.dto.EnrollementInterface;
import com.example.librarymanager.dto.RemoveEnrollmentRequest;
import com.example.librarymanager.exceptions.CourseNotPresentException;
import com.example.librarymanager.exceptions.EmptyDatabaseExeception;
import com.example.librarymanager.exceptions.StudentAlreadyEnrolledException;
import com.example.librarymanager.exceptions.StudentNotPresentException;
import com.example.librarymanager.models.Course;
import com.example.librarymanager.models.Student;
import com.example.librarymanager.repository.CourseRepository;
import com.example.librarymanager.repository.StudentRepository;
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


    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
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

// TODO : Check if it is more necessary
    private Optional<Course> getCourseIfPresent(EnrollementInterface addEnrollementRequest) {
        // vf existenta cursului
        Optional<Course> course = courseRepository.findById(addEnrollementRequest.getIdCourse().longValue());

        if (course.isEmpty()) {
            throw new CourseNotPresentException("This ocurse is not present !");
        }

        return course;
    }

    private Optional<Student> getStudentIfPresent(EnrollementInterface addEnrollementRequest) {
        // vf existenta studentului
        Optional<Student> student = studentRepository.findById(addEnrollementRequest.getIdStudent().longValue());
        if (student.isEmpty()) {
            throw new StudentNotPresentException("This student is not preset !");
        }

        return student;
    }

    @Transactional
    @Modifying
    public void removeStudent(Student student){
        studentRepository.delete(student);
    }

    @Transactional
    @Modifying
    public void removeEnrolment(Student student, Course course){
        if(student.getEnrolledCourses().contains(course)) {
            student.removeCourse(course);
            studentRepository.saveAndFlush(student);
        }
    }

    @Transactional
    @Modifying
    public void addEnrolment(Student student, Course course){
        // TODO :
        // se verifica ca exista cursul

        // se verifica ca exista studentul

        if( ! student.getEnrolledCourses().contains(course)) {
            student.addCourse(course);
            studentRepository.saveAndFlush(student);
        }
    }

}
