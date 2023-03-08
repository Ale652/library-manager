package com.example.librarymanager.services;

import com.example.librarymanager.dto.AddBookToStudentRequest;
import com.example.librarymanager.dto.AddEnrollementRequest;
import com.example.librarymanager.dto.EnrollementInterface;
import com.example.librarymanager.dto.RemoveBookFromStudentRequest;
import com.example.librarymanager.exceptions.*;
import com.example.librarymanager.models.Book;
import com.example.librarymanager.models.Course;
import com.example.librarymanager.models.Student;
import com.example.librarymanager.repository.BookRepository;
import com.example.librarymanager.repository.CourseRepository;
import com.example.librarymanager.repository.StudentRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    private CourseRepository courseRepository;

    private BookRepository bookRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository,
                          BookRepository bookRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.bookRepository = bookRepository;
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
            throw new CourseNotPresentException("This course is not present !");
        }

        return course;
    }

    private Optional<Student> getStudentIfPresent(Long student_id) {
        // vf existenta studentului
        Optional<Student> student = studentRepository.findById(student_id);
        if (student.isEmpty()) {
            studentNotFoundException();
        }

        return student;
    }

    private Optional<Book> getBookIfPresent(Long book_id){
        Optional<Book> book =  bookRepository.findById(book_id);

        if(book.isEmpty()){
            throw new BookNotPresentException("This book is not present !");
        }

        return book;
    }

    public Optional<List<Student>> findAllByBooks_TitleLike(String title){ return studentRepository.findAllByBooks_TitleLike(title); }

    public Optional<List<Student>> findAllByEnrolledCourses_NameLike(String courseName){ return studentRepository.findAllByEnrolledCourses_NameLike(courseName); }

    private void studentNotFoundException() {
        throw new StudentNotPresentException("This student is not preset !");
    }

    private void throwExceptionDatabaseEmpty() {
        throw new EmptyDatabaseExeception("No student with max books.");
    }

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
        Student student = getStudentIfPresent(addEnrollementRequest.getIdStudent().longValue()).get();

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
        Student student = getStudentIfPresent(removeEnrollmentRequest.getIdStudent().longValue()).get();

        checkIfStudentEnrolledAtTheCourse(course, student);

        this.removeEnrolment(student, course);
        studentRepository.saveAndFlush(student);
    }

    @Transactional
    @Modifying
    public Book addBookToStudent(AddBookToStudentRequest addBookToStudentRequest){

        Long book_id = addBookToStudentRequest.getIdBook().longValue();
        Long student_id = addBookToStudentRequest.getIdStudent().longValue();
        Student student = getStudentIfPresent(student_id).get();
        Book book = getBookIfPresent(book_id).get();
        //TODO: check if it is ok - logically :
        checkIfBookAllreadyAddedToAStudent(book_id);
        studentRepository.addBookToStudent(student, book_id);
        return book;
    }

    @Transactional
    @Modifying
    public Book removeBookFromStudent(RemoveBookFromStudentRequest removeBookFromStudentRequest){
        Long book_id = removeBookFromStudentRequest.getIdBook().longValue();
        Long student_id = removeBookFromStudentRequest.getIdStudent().longValue();
        Student student = getStudentIfPresent(student_id).get();
        Book book = getBookIfPresent(book_id).get();
        checkIfBookAlreadyAssignedToStudent(book_id, student);
        studentRepository.removeBookFromStudent(book_id);
        return book;
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

    private void checkIfBookAllreadyAddedToAStudent(Long id_book){
        Optional<Student> student = studentRepository.checkIfBookAllreadyAddedToAStudent(id_book);

        if(! student.isEmpty()){
            throw new StudentNotPresentException("The book is already assigned to a student !");
        }
    }

    private void checkIfBookAlreadyAssignedToStudent(Long id_book, Student student){
        Optional<Student> studentDB = studentRepository.checkIfBookAllreadyAddedToAStudent(id_book);

        if(! student.equals(studentDB.get())){
            throw new BookNotPresentException("The book is assigned to antoher student.");
        }
        else if(studentDB.isEmpty())
            throw new BookNotPresentException("No student has this book assigned.");
    }
}

