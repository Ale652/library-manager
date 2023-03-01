package com.example.librarymanager.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Table(name="course")
@Entity(name="Course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Course {

    @Id
    @SequenceGenerator(
            name="course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long id;

    @NotNull
    @Size(min = 2, message = "Numele are cel putin 2 litere!")
    private String name;

    @NotNull
    @Size(min = 2, message = "Departamentul are cel putin 2 litere !")
    private String department;

    @ManyToMany(
            mappedBy = "enrolledCourses",
            fetch = FetchType.EAGER
    )
    @JsonBackReference
    List<Student> students  = new ArrayList<>();

    @Override
    public boolean equals(Object obj){

        return  this.getId()==((Course)obj).getId();
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
