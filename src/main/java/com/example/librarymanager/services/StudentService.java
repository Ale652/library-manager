package com.example.librarymanager.services;

import com.example.librarymanager.dto.AddEnrollementRequest;
import com.example.librarymanager.dto.EnrollementInterface;
import com.example.librarymanager.dto.RemoveEnrollmentRequest;
import com.example.librarymanager.exceptions.CourseNotPresentException;
import com.example.librarymanager.exceptions.EmptyDatabaseExeception;
import com.example.librarymanager.exceptions.StudentAlreadyEnrolledException;
import com.example.librarymanager.exceptions.StudentNotPresentException;
import com.example.librarymanager.models.Book;
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

    //TODO : make a function to return the Type expected or an Error

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

    public List<Student> findByAgeGreaterThan(Integer age){
        return studentRepository.findByAgeGreaterThan(age);
    }

    public List<Student> findByAgeLessThan(Integer age){ return studentRepository.findByAgeLessThan(age); }

    public List<Optional<Student>> findTopByOrderByAgeAsc(){
        return studentRepository.findTopByOrderByAgeAsc();
    }

    public List<Optional<Student>> findTopByOrderByAgeDesc(){ return studentRepository.findTopByOrderByAgeDesc(); }

    public List<String> getListOfStudentNameWithMaxBooks() {
        Optional<List<String>> studentWithMaxBooks = studentRepository.selectStudentWithMaxBooks();

        if (studentWithMaxBooks.isEmpty()) {

            throw new EmptyDatabaseExeception("No student with max books.");
        }
        return studentWithMaxBooks.get();
    }

    public Optional<List<Student>> findTop10ByOrderByAgeDesc(){ return studentRepository.findTop10ByOrderByAgeDesc(); }

    public Optional<List<Student>> findTop10ByOrderByAgeAsc(){ return studentRepository.findTop10ByOrderByAgeAsc(); }

    public List<Student> findByEmailLike(String likePattern){ return studentRepository.findByEmailLike(likePattern); }

    public List<Student> findByFirstNameLikeAndSecondNameLike(String firstName, String secondName){
        return studentRepository.findByFirstNameLikeAndSecondNameLike(firstName, secondName); }

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


    // Enrollmentn\UnEnrollment
    @Transactional
    @Modifying
    public void addEnrolment(Student student, Course course){

        if( ! student.getEnrolledCourses().contains(course)) {
            student.addCourse(course);
            studentRepository.saveAndFlush(student);
        }
    }

    @Transactional
    @Modifying
    public void addEnrolment(AddEnrollementRequest addEnrollementRequest) {

        Course course = getCourseIfPresent(addEnrollementRequest).get();
        Student student = getStudentIfPresent(addEnrollementRequest).get();

        checkIfStudentAlreadyEnrolledAtTheCourse(course, student);

        this.addEnrolment(student, course);
    }

    private void checkIfStudentAlreadyEnrolledAtTheCourse(Course course, Student student) {
        if( student.getEnrolledCourses().contains(course) ) {
            throw new StudentAlreadyEnrolledException("This student is already enrolled to this course !");
        }
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
    public void removeEnrolment(EnrollementInterface removeEnrollmentRequest){

        Course course = getCourseIfPresent(removeEnrollmentRequest).get();
        Student student = getStudentIfPresent(removeEnrollmentRequest).get();

        checkIfStudentEnrolledAtTheCourse(course, student);

        this.removeEnrolment(student, course);
        studentRepository.saveAndFlush(student);
    }

    private void checkIfStudentEnrolledAtTheCourse(Course course, Student student) {
        if (! student.getEnrolledCourses().contains(course)) {
            throw new StudentAlreadyEnrolledException("Impossible unenrollment !" +
                    "This student is not already enrolled to this course !");
        }
    }

    //TODO : addBook\removeBook
    // Quetion ? Between book and student why to do not do many to many =>
    // many students to have many books
    // many books to be used by many students
    // This limitation do not permit to have a book for many students
    // => a book may be used by many studenst, because there are n instances of this book in that library
    // why not keep an history on what a student have had ever as books used



}

