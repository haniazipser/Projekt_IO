package com.example.Projekt_IO.Model.Dtos;

import com.example.Projekt_IO.Model.Entities.DeclarationStatus;
import com.example.Projekt_IO.Model.Entities.ExerciseDeclaration;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
        this.declarationDate = declaration.getDeclarationDate().truncatedTo(ChronoUnit.MINUTES);
        this.declarationStatus = declaration.getDeclarationStatus();
        this.exercise = new ExerciseDto(declaration.getExercise());
        this.student = declaration.getStudent();
    }
}
