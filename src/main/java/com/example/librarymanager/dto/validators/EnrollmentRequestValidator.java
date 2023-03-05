package com.example.librarymanager.dto.validators;

import com.example.librarymanager.dto.AddEnrollementRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnrollmentRequestValidator implements ConstraintValidator<ValidEnrollmentRequest, AddEnrollementRequest> {

    @Override
    public boolean isValid(AddEnrollementRequest addEnrollementRequest, ConstraintValidatorContext constraintValidatorContext) {

        return  addEnrollementRequest.getIdCourse()>0&&addEnrollementRequest.getIdStudent()>0;
    }
}
