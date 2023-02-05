package com.example.librarymanager.repository;

import com.example.librarymanager.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
