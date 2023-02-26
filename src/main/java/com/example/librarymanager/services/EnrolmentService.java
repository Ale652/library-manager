//package com.example.librarymanager.services;
//
//import com.example.librarymanager.models.Enrolment;
//import com.example.librarymanager.repository.EnrollementRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class EnrolmentService {
//    private EnrollementRepository enrollementRepository;
//
//    public EnrolmentService(EnrollementRepository enrollementRepository){
//        this.enrollementRepository = enrollementRepository;
//    }
//
//    @Transactional
//    @Modifying
//    public void removeEnrolment(Long id){
//
//        Enrolment enrolment = enrollementRepository.findById(id).get();
//
//
//        enrolment.setStudent(null);
//        enrolment.setCourse(null);
//
////
////        enrollementRepository.delete(enrolment);
////
////        enrollementRepository.deleteById(id);
//
////        enrollementRepository.flush();
//    }
//
//}
