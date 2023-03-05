package com.example.librarymanager.dto.validators;

import com.example.librarymanager.dto.RemoveEnrollmentRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UnenrollmentRequestValidator implements ConstraintValidator<ValidEnrollmentRequest, RemoveEnrollmentRequest> {

    @Override
    public boolean isValid(RemoveEnrollmentRequest removeEnrollmentRequest, ConstraintValidatorContext constraintValidatorContext) {
        return removeEnrollmentRequest.getIdCourse() > 0 && removeEnrollmentRequest.getIdStudent() > 0;
    }
}
