package com.example.Projekt_IO.Model.Entities;

import com.example.Projekt_IO.Model.DeclarationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "exercise_declaration")
public class ExerciseDeclaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime declarationDate;

    @Enumerated(EnumType.STRING)
    private DeclarationStatus declarationStatus;

    @ManyToOne
    private Exercise exercise;

    private String student;

    public ExerciseDeclaration(){};
}
