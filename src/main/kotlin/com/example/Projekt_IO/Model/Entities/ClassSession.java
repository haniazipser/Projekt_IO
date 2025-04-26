package com.example.Projekt_IO.Model.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "class_session")
public class ClassSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime classDate;
    @ManyToOne
    private ClassGroup classGroup;
    @OneToMany(mappedBy = "classSession")
    private Set<Exercise> classExercises;

    public ClassSession(){}

}
