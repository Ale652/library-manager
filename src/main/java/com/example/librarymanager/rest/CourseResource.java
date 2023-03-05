package com.example.librarymanager.rest;

import com.example.librarymanager.dto.CourseListResponse;
import com.example.librarymanager.models.Book;
import com.example.librarymanager.models.Course;
import com.example.librarymanager.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
public class CourseResource {

    private CourseService courseService;
    public CourseResource(CourseService courseService){ this.courseService = courseService; }

    @GetMapping("/")
    public ResponseEntity<CourseListResponse> courseList(){
        CourseListResponse courseListResponse = CourseListResponse.builder().courseList(courseService.getAllCourses())
                .message("All courses")
                .build();

        return new ResponseEntity<>(courseListResponse, HttpStatus.OK);
    }


}
