package com.example.librarymanager.services;

import com.example.librarymanager.repository.EnrollementRepository;
import org.springframework.stereotype.Service;

@Service
public class EnrolmentService {
    private EnrollementRepository enrollementRepository;

    public EnrolmentService(EnrollementRepository enrollementRepository){
        this.enrollementRepository = enrollementRepository;
    }
}
