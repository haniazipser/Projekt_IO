package com.example.Projekt_IO.Model.Dtos;

import com.example.Projekt_IO.Model.Entities.ExerciseDeclaration;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class DeclarationShortDto {
    private String student;
    private UUID exerciseId;
    private Double pointsInCourse;

    public DeclarationShortDto(ExerciseDeclaration exerciseDeclaration, Double points){
        this.student = exerciseDeclaration.getStudent();
        this.exerciseId = exerciseDeclaration.getExercise().getId();
        this.pointsInCourse = points;
    }
}
