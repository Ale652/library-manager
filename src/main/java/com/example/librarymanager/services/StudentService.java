package com.example.librarymanager.services;

import com.example.librarymanager.dto.AddEnrollementRequest;
import com.example.librarymanager.dto.EnrollementInterface;
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

            throwExceptionDatabaseEmpty();
        }

        return students;

    }

    public List<Student> findByAgeGreaterThan(Integer age){

        List<Student> students = studentRepository.findByAgeGreaterThan(age);

        if (students.size() == 0) {

            throwExceptionDatabaseEmpty();
        }

        return students;
    }

    public List<Student> findByAgeLessThan(Integer age){

        List<Student> students = studentRepository.findByAgeLessThan(age);

        if (students.size() == 0) {

            throwExceptionDatabaseEmpty();
        }

        return students;
    }

    public List<Optional<Student>> findTopByOrderByAgeAsc(){

        List<Optional<Student>> students = studentRepository.findTopByOrderByAgeAsc();

        if (students.size() == 0) {

            throwExceptionDatabaseEmpty();
        }
        return students;
    }

    public List<Optional<Student>> findTopByOrderByAgeDesc(){

        List<Optional<Student>> students = studentRepository.findTopByOrderByAgeDesc();;

        if (students.size() == 0) {

            throwExceptionDatabaseEmpty();
        }
        return students;
    }

    public List<String> getListOfStudentNameWithMaxBooks() {
        Optional<List<String>> studentWithMaxBooks = studentRepository.selectStudentsWithMaxBooks();

        if (studentWithMaxBooks.isEmpty()) {

            throwExceptionDatabaseEmpty();
        }
        return studentWithMaxBooks.get();
    }

    public Optional<List<Student>> findTop10ByOrderByAgeDesc(){

        Optional<List<Student>> students = studentRepository.findTop10ByOrderByAgeDesc();

        if (students.get().size() == 0) {

            throwExceptionDatabaseEmpty();
        }
        return students;
        }

    public Optional<List<Student>> findTop10ByOrderByAgeAsc(){

        Optional<List<Student>> students = studentRepository.findTop10ByOrderByAgeAsc();

        if (students.get().size() == 0) {

            throwExceptionDatabaseEmpty();
        }
        return students;

    }

    public List<Student> findByEmailLike(String likePattern){

        List<Student>students = studentRepository.findByEmailLike(likePattern);

        if (students.size() == 0) {

            throwExceptionDatabaseEmpty();
        }
        return students;
    }

    public List<Student> findByFirstNameLikeAndSecondNameLike(String firstName, String secondName){

        List<Student>students = studentRepository.findByFirstNameLikeAndSecondNameLike(firstName, secondName);

        if (students.size() == 0) {

            studentNotFoundException();
        }
        return students;
    }

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
            studentNotFoundException();
        }

        return student;
    }

    public Optional<List<Student>> findAllByBooks_TitleLike(String title){ return studentRepository.findAllByBooks_TitleLike(title); }

    public Optional<List<Student>> findAllByEnrolledCourses_NameLike(String courseName){ return studentRepository.findAllByEnrolledCourses_NameLike(courseName); }

    private void studentNotFoundException() {
        throw new StudentNotPresentException("This student is not preset !");
    }

    private void throwExceptionDatabaseEmpty() {
        throw new EmptyDatabaseExeception("No student with max books.");
    }


    // Enrollmentn\UnEnrollment
    @Transactional
    @Modifying
    public void addEnrolment(Student student, Course course){

            student.addCourse(course);
            studentRepository.saveAndFlush(student);
    }

    @Transactional
    @Modifying
    public void addEnrolment(AddEnrollementRequest addEnrollementRequest) {

        Course course = getCourseIfPresent(addEnrollementRequest).get();
        Student student = getStudentIfPresent(addEnrollementRequest).get();

        checkIfStudentAlreadyEnrolledAtTheCourse(course, student);

        this.addEnrolment(student, course);
    }


    @Transactional
    @Modifying
    public void removeStudent(Student student){
        studentRepository.delete(student);
    }

    @Transactional
    @Modifying
    public void removeEnrolment(Student student, Course course){
            student.removeCourse(course);
            studentRepository.saveAndFlush(student);
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

    private void checkIfStudentAlreadyEnrolledAtTheCourse(Course course, Student student) {
        if( student.getEnrolledCourses().contains(course) ) {
            throw new StudentAlreadyEnrolledException("This student is already enrolled to this course !");
        }
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

