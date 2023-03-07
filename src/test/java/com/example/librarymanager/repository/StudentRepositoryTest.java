package com.example.librarymanager.repository;

import com.example.librarymanager.models.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StudentRepositoryTest {
    //TODO : QUESTION : Why here is not working if I try to inject through constructor as done on non testing classes ?

    @Autowired
    StudentRepository studentRepository;

    @Test
    public void check_uniquess_email_in_DB(){
        String email = "twitham1@sogou.com";

        List<Student> students = studentRepository.findByEmailLike(email);

        assertTrue(students.size() == 1);
    }



}
