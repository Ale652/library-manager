package com.example.librarymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class StudentListWithMaxNrBooksResponse {
    String message;
    List<String> listOfStudentsWithMaxNrBooks;
}
