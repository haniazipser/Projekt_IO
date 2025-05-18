package com.example.Projekt_IO.Model.Dtos;

import com.example.Projekt_IO.Model.DeclarationStatus;
import com.example.Projekt_IO.Model.Entities.Exercise;
import com.example.Projekt_IO.Model.Entities.ExerciseDeclaration;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
public class DeclarationDto {
    private UUID id;
    private Instant declarationDate;

    private DeclarationStatus declarationStatus;

    private ExerciseDto exercise;

    private String student;

    public DeclarationDto(ExerciseDeclaration declaration){
        this.id = declaration.getId();
        this.declarationDate = declaration.getDeclarationDate();
        this.declarationStatus = declaration.getDeclarationStatus();
        this.exercise = new ExerciseDto(declaration.getExercise());
        this.student = declaration.getStudent();
    }
}
