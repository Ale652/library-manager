package com.example.librarymanager.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

public interface EnrollementInterface {
    public Integer idStudent= 0;

    public  Integer idCourse = 0;

    public default Integer getIdCourse(){ return idCourse;}
    public default Integer getIdStudent(){ return idStudent;}
}
