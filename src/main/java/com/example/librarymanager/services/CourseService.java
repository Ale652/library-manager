package com.example.librarymanager.services;

import com.example.librarymanager.exceptions.EmptyDatabaseExeception;
import com.example.librarymanager.models.Book;
import com.example.librarymanager.models.Course;
import com.example.librarymanager.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    //TODO: General : getAllBooks, getBooksByAuthor, getAllStudentsThatHaveBook-X (needModifyQuantityForBookInModel)..

    CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){ this.courseRepository = courseRepository; }

    public List<Course> getAllCourses(){
        List<Course> courses = courseRepository.findAll();

        if(courses.size()==0){

            throw  new EmptyDatabaseExeception("Dtabase empty ");
        }

        return  courses;
    }

    public Course getCourseByNameLike(String course){ return courseRepository.findByNameLike(course); }

    public  List<Course> findByDepartmentLike(String department){ return courseRepository.findByDepartmentLike(department); }

}
