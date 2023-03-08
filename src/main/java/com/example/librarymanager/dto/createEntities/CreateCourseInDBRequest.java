package com.example.librarymanager.dto.createEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class CreateCourseInDBRequest {
     Long id;
     String name;
     String department;
}
