package com.example.librarymanager.dto;

import com.example.librarymanager.models.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class CourseListResponse {
    String message;
    List<Course> courseList;
}
