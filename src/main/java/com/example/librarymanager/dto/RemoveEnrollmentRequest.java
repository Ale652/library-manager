package com.example.librarymanager.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemoveEnrollmentRequest implements EnrollementInterface{
    @NotNull
    private Integer idStudent;
    @NotNull
    private Integer idCourse;
}
