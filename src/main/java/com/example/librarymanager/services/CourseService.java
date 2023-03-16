package com.example.librarymanager.services;

import com.example.librarymanager.exceptions.EmptyDatabaseExeception;
import com.example.librarymanager.models.Book;
import com.example.librarymanager.models.Course;
import com.example.librarymanager.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){ this.courseRepository = courseRepository; }

    public List<Course> getAllCourses(){
        List<Course> courses = courseRepository.findAll();

        if(courses.size()==0){

            throw  new EmptyDatabaseExeception("Dtabase empty ");
        }

        return  courses;
    }

    public Optional<List<Course>> getCoursesByNameLike(String course){ return courseRepository.findByNameLike(course); }

    public Optional<List<Course>>  getCoursesByDepartmentLike(String department){ return courseRepository.findByDepartmentLike(department); }

    public Optional<List<Course>> getTop10ByOrderByNameAsc(){ return courseRepository.findTop10ByOrderByNameAsc(); }

    public Optional<List<Course>> getTop10ByOrderByNameDesc(){ return courseRepository.findTop10ByOrderByNameDesc(); }

    public Optional<List<Course>> getTop10ByOrderByDepartmentAsc(){ return courseRepository.findTop10ByOrderByDepartmentAsc(); }

    public Optional<List<Course>> getTop10ByOrderByDepartmentDesc(){ return courseRepository.findTop10ByOrderByDepartmentDesc(); }

    public Optional<List<Course>> getCorusesByNameEquals(String course){ return courseRepository.findByNameEquals(course); }

    public Optional<List<Course>> findAllByStudents_Email(String emailStudent){ return courseRepository.findAllByStudents_Email(emailStudent); }

    public Optional<List<Course>> findByDepartmentEquals(String course){ return courseRepository.findByDepartmentEquals(course); }

    public Course createCourseInDB(Course course){
        courseRepository.saveAndFlush(course);
        return course;
    }

    public Course removeCourseInDB(Long id){
        Course course = courseRepository.findById(id).get();
        courseRepository.deleteById(id);
        return course;
    }

    public Optional<List<Course>> getMostPopularCourse(){
        return courseRepository.getMostPopularCourse();
    }

    public Page<Course> getMostPopular3Courses(){ return courseRepository.getMostPopularCourses(PageRequest.of(1, 3));}
}
