package com.example.librarymanager.repository;

import com.example.librarymanager.LibraryManagerApplication;
import com.example.librarymanager.models.Student;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LibraryManagerApplication.class)
public class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @BeforeEach
    void cleanup(){

         studentRepository.deleteAll();
    }

    @Test
    @Transactional
    public void check_uniquess_email_in_DB(){



        //precondite
        Student student1= Student.builder()
                .age(0)
                .email("tes1@gmail.com")
                .firstName("tess")
                .secondName("ceva").build();

        Student student2= Student.builder()
                .age(0)
                .email("test2@gmail.com")
                .firstName("tess")
                .secondName("ceva").build();
        Student student3= Student.builder()
                .age(0)
                .email("test3@gmail.com")
                .firstName("tess")
                .secondName("ceva").build();
        List<Student> initialStudents = new ArrayList<>(List.of(student1,student2,student3));
        studentRepository.saveAll(initialStudents);

        List<Student> students=studentRepository.findByEmailLike("%test%");

        //rezultat

//        assertTrue(students.size() == 2);

        assertEquals(2,students.size());
    }




}
