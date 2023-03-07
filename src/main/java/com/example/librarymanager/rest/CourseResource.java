package com.example.librarymanager.rest;

import com.example.librarymanager.dto.CourseListResponse;
import com.example.librarymanager.exceptions.TooManyCoursesReturnedException;
import com.example.librarymanager.models.Book;
import com.example.librarymanager.models.Course;
import com.example.librarymanager.services.CourseService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

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

    @GetMapping("/coursesByNameLike")
    public ResponseEntity<CourseListResponse> courseByNameLike(@RequestParam String course){

            CourseListResponse courseListResponse = CourseListResponse.builder()
                    .courseList(courseService.getCoursesByNameLike(course).get())
                    .message("course By Name Like")
                    .build();

            return new ResponseEntity<>(courseListResponse, HttpStatus.OK);
    }

    @GetMapping("/coursesByNameEquals")
    public ResponseEntity<CourseListResponse> courseByNameEquals(@RequestParam String course){
        CourseListResponse courseListResponse = CourseListResponse.builder()
                .courseList(courseService.getCorusesByNameEquals(course).get())
                .message("courses By Name Equals")
                .build();

        return new ResponseEntity<>(courseListResponse, HttpStatus.OK);
    }

    @GetMapping("/coursesByDepartmentLike")
    public ResponseEntity<CourseListResponse> courseByDepartmentLike(@RequestParam String department){

        CourseListResponse courseListResponse = CourseListResponse.builder()
                .courseList(courseService.getCoursesByDepartmentLike(department).get())
                .message("courses By Department Like")
                .build();

        return new ResponseEntity<>(courseListResponse, HttpStatus.OK);
    }

    @GetMapping("/coursesByDepartmentEquals")
    public ResponseEntity<CourseListResponse> courseByDepartmentEquals(@RequestParam String department){
        CourseListResponse courseListResponse = CourseListResponse.builder()
                .courseList(courseService.getCoursesByDepartmentLike(department).get())
                .message("courses By Department Equals")
                .build();

        return new ResponseEntity<>(courseListResponse, HttpStatus.OK);
    }

    @GetMapping("/listOfCoursesTop10ByOrderByNameAsc")
    public ResponseEntity<CourseListResponse> listOfCoursesTop10ByOrderByNameAsc(){
        CourseListResponse courseListResponse = CourseListResponse.builder()
                .courseList(courseService.getTop10ByOrderByNameAsc().get())
                .message("list Of Courses Top 10 By Order By Name Asc")
                .build();

        return new ResponseEntity<>(courseListResponse, HttpStatus.OK);
    }

    @GetMapping("/listOfCoursesTop10ByOrderByNameDesc")
    public ResponseEntity<CourseListResponse> listOfCoursesTop10ByOrderByNameDesc(){
        CourseListResponse courseListResponse = CourseListResponse.builder()
                .courseList(courseService.getTop10ByOrderByNameDesc().get())
                .message("list Of Courses Top 10 By Order By Name Desc")
                .build();

        return new ResponseEntity<>(courseListResponse, HttpStatus.OK);
    }
    
    @GetMapping("/listOfCoursesTop10ByOrderByDepartmentAsc")
    public ResponseEntity<CourseListResponse> listOfCoursesTop10ByOrderByDepartmentAsc(){
        CourseListResponse courseListResponse = CourseListResponse.builder()
                .courseList(courseService.getTop10ByOrderByDepartmentAsc().get())
                .message("list Of Courses Top 10 By Order By Department Asc")
                .build();

        return new ResponseEntity<>(courseListResponse, HttpStatus.OK);
    }

    @GetMapping("/listOfCoursesTop10ByOrderByDepartmentDesc")
    public ResponseEntity<CourseListResponse> listOfCoursesTop10ByOrderByDepartmentDesc(){
        CourseListResponse courseListResponse = CourseListResponse.builder()
                .courseList(courseService.getTop10ByOrderByDepartmentDesc().get())
                .message("list Of Courses Top 10 By Order By Department Desc")
                .build();

        return new ResponseEntity<>(courseListResponse, HttpStatus.OK);
    }

}
