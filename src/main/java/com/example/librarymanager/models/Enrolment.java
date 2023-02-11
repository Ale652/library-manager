package com.example.librarymanager.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;

@Table(name = "enrolment")
@Entity(name = "Enrolment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Enrolment {
    @Id
    @SequenceGenerator(
            name = "enrolment_sequence",
            sequenceName = "enrolment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "enrolment_sequence"
    )
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_course")
    private Course course;

    @NotNull
    LocalDateTime created_at;

    @Override
    public String toString() {
        return "Enrolment{" +
                "id=" + id +
                ", student=" + student.getFirstName() + " " + student.getSecondName() +
                ", course=" + course.getName() +
                ", created_at=" + created_at +
                '}';
    }
}
