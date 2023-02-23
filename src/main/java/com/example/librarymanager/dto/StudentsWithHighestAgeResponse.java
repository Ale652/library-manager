package com.example.librarymanager.dto;

import com.example.librarymanager.models.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class StudentsWithHighestAgeResponse {
    String message;
    List<String> listOfStudentsWithHighestAge;
}
