//package com.example.librarymanager.repository;
//
//import com.example.librarymanager.models.Enrolment;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.Optional;
//
//public interface EnrollementRepository extends JpaRepository<Enrolment, Long> {
//    //check if an student is already enrolled to an given course
//    @Query("select e from Enrolment e where e.student.id = ?1 and e.course.id = ?2")
//    Optional<Enrolment> findEnrolment(Long idStudent, Long idCourse);
//}
