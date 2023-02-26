package com.example.librarymanager.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


@Table(name="student")
@Entity(name="Student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator =  "student_sequence")
    Long id;

    @Column(name = "first_name",
            nullable = false)
    String firstName;

    @Column(name = "second_name",
            nullable = false)
    String secondName;

    @Column(name = "email",
            nullable = false)
    @Email
    String email;

    @DecimalMax(value = "25", message = "Cursul este alocat persoanelor sub 25 de ani")
    @Column(name = "age",
            nullable = false)
    double age;


    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    List<Book> books= new ArrayList<>();

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(name = "enrolment",
     joinColumns = { @JoinColumn(name="student_id")},
      inverseJoinColumns ={@JoinColumn(name="course_id")})
     @JsonManagedReference
      List<Course> enrolledCourses = new ArrayList<>();

    public void addBook(Book book){
        this.books.add(book);
        book.setStudent(this);
    }

    public void addCourse(Course course){

         enrolledCourses.add(course);
    }


    public void removeCourse(Course course){

         enrolledCourses.remove(course);
    }
//
//    public void addEnrolment(Enrolment enrolment){
//        this.enrolments.add(enrolment);
//        enrolment.setStudent(this);
//    }
//
//
//    public void removeEnrolment(Enrolment enrolment){
//        this.enrolments.remove(enrolment);
//        enrolment.setStudent(null);
//    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
