package com.example.librarymanager.dto;

import com.example.librarymanager.dto.validators.ValidEnrollmentRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ValidEnrollmentRequest
public class AddEnrollementRequest implements  EnrollementInterface{
    @NotNull
    private Integer idStudent;
    @NotNull
    private Integer idCourse;
}
