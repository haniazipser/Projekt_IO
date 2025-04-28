package com.example.Projekt_IO.Model.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "exercise")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Lesson lesson;
    private Integer exerciseNumber;
    private String subpoint;

    @OneToMany(mappedBy = "source")
    private Set<Point> solvers;

    @OneToMany(mappedBy = "exercise")
    private Set<ExerciseDeclaration> declarations;

    private String approvedStudent;

    public Exercise(){}
}
