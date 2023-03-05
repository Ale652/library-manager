package com.example.librarymanager.dto.validators;


import com.example.librarymanager.dto.validators.EnrollmentRequestValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.example.librarymanager.configuration.ErrorConstants.MSG_VALIDATOR_ENROLEMNT_REQUEST;

@Constraint(validatedBy = EnrollmentRequestValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface ValidEnrollmentRequest {

     String message()  default MSG_VALIDATOR_ENROLEMNT_REQUEST;
     Class<?>[] groups() default {};
     Class<? extends Payload>[] payload() default {};




}
