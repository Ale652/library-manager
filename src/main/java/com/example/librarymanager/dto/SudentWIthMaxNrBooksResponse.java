package com.example.librarymanager.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

;

@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class SudentWIthMaxNrBooksResponse {
    private String message;
    private String student;
}
