package com.example.librarymanager.services;

import com.example.librarymanager.exceptions.EmptyDatabaseExeception;
import com.example.librarymanager.models.Student;
import com.example.librarymanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;


    public List<Student> getAllStudents(){

        List<Student> students=studentRepository.findAll();

        if(students.size()==0){

            throw  new EmptyDatabaseExeception("Dtabase empty ");
        }

        return  students;

    }

    public String getStudentNameWithMaxBooks(){
        Optional<List<String>> studentWithMaxBooks=studentRepository.selectStudentWithMaxBooks();

        if(studentWithMaxBooks.isEmpty()){

              throw new EmptyDatabaseExeception("No student with max books.");
        }
        return studentWithMaxBooks.get().stream().map(s -> s).collect(Collectors.joining());
    }

    public List<String> getListOfStudentNameWithMaxBooks(){
        Optional<List<String>> studentWithMaxBooks=studentRepository.selectStudentWithMaxBooks();

        if(studentWithMaxBooks.isEmpty()){

            throw new EmptyDatabaseExeception("No student with max books.");
        }
        return studentWithMaxBooks.get();
    }


    public List<String> findHigherAgeStudent(){
        Optional<List<String>> studentWithHighestAge = studentRepository.findHigherAgeStudent();

        if(studentWithHighestAge.isEmpty()){

            throw new EmptyDatabaseExeception("No student with max age");
        }
        return studentWithHighestAge.get();
    }

    public List<String> findLowestAgeStudent(){
        Optional<List<String>> studentWithLowestAge = studentRepository.findLowerAgeStudent();

        if(studentWithLowestAge.isEmpty()){

            throw new EmptyDatabaseExeception("No student with max age");
        }
        return studentWithLowestAge.get();
    }

}
