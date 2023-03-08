package com.example.librarymanager.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemoveBookFromStudentRequest {
    @NotNull
    private Long idStudent;

    @NotNull
    private Long idBook;
}
