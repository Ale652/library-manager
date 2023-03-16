package com.example.librarymanager.rest;

import com.example.librarymanager.dto.CourseListResponse;
import com.example.librarymanager.dto.RemoveCourseFromDBRequest;
import com.example.librarymanager.dto.createEntities.CreateCourseInDBRequest;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @GetMapping("/findAllByStudents_Email")
    public ResponseEntity<CourseListResponse> findAllByStudents_Email(@RequestParam String emailStudent){
        CourseListResponse courseListResponse = CourseListResponse.builder()
                .courseList(courseService.findAllByStudents_Email(emailStudent).get())
                .message("Get courses where student enrolled - using the email adress")
                .build();

        return new ResponseEntity<>(courseListResponse, HttpStatus.OK);
    }

    @GetMapping("/findByDepartmentEquals")
    public ResponseEntity<CourseListResponse> findByDepartmentEquals(@RequestParam String courseName){
        CourseListResponse courseListResponse = CourseListResponse.builder()
                .courseList(courseService.findByDepartmentEquals(courseName).get())
                .message("Get courses by department name " + courseName)
                .build();

        return new ResponseEntity<>(courseListResponse, HttpStatus.OK);
    }

    @PostMapping("/createCourseInDB")
    public ResponseEntity<CourseListResponse> createCourseInDB(@RequestBody CreateCourseInDBRequest createCourseInDBRequest){
        Course course = Course.builder()
                .id(createCourseInDBRequest.getId())
                .name(createCourseInDBRequest.getName())
                .department(createCourseInDBRequest.getDepartment())
                .build();

        CourseListResponse courseListResponse = CourseListResponse.builder()
                .courseList(Stream.of(courseService.createCourseInDB(course)).collect(Collectors.toList()))
                .message("A new course created")
                .build();

        return new ResponseEntity(courseListResponse, HttpStatus.OK);
    }

    @PostMapping("/removeCourseInDB")
    public ResponseEntity<CourseListResponse> removeCourseInDB(@RequestBody RemoveCourseFromDBRequest removeCourseFromDBRequest){
        Long courseId = removeCourseFromDBRequest.getId();

        CourseListResponse courseListResponse = CourseListResponse.builder()
                .courseList(Stream.of(courseService.removeCourseInDB(courseId)).toList())
                .message("Course removed "+courseId+"from DB")
                .build();

        return new ResponseEntity<>(courseListResponse, HttpStatus.OK);
    }

    @GetMapping("/getMostPopularCourse")
    public ResponseEntity<CourseListResponse> getMostPopularCourse(){
        CourseListResponse courseListResponse = CourseListResponse.builder()
                .courseList(courseService.getMostPopularCourse().get())
                .message("Get most popular course")
                .build();

        return  new ResponseEntity<>(courseListResponse, HttpStatus.OK);
    }

    @GetMapping("/findMostPopular3Courses")
    public ResponseEntity<CourseListResponse> findMostPopular3Courses(){
        CourseListResponse courseListResponse = CourseListResponse.builder()
                .courseList(courseService.getMostPopular3Courses().stream().toList())
                .message("Get most 3 popular courses.")
                .build();

        return new ResponseEntity<>(courseListResponse, HttpStatus.OK);
    }
}
