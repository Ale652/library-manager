package com.example.librarymanager.dto;

import com.example.librarymanager.dto.validators.ValidEnrollmentRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBookToStudentRequest {
    @NotNull
    private Long idStudent;

    @NotNull
    private Long idBook;
}
