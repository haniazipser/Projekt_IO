package com.example.Projekt_IO.Model.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime classDate;
    @ManyToOne
    private Course course;
    @OneToMany(mappedBy = "lesson")
    private Set<Exercise> classExercises;

    public Lesson(){}

}
