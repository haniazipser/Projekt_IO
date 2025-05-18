package com.example.Projekt_IO.Model.Dtos;

import com.example.Projekt_IO.Model.Entities.Exercise;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;
@Getter @Setter
public class ExerciseWithPointsDto {
    private UUID id;
    private Integer exerciseNumber;
    private String subpoint;
    private String approvedStudent;
    private Double approvedStudentsPoints;
    public ExerciseWithPointsDto(Exercise exercise, Double points){
        this.id=exercise.getId();
        this.exerciseNumber = exercise.getExerciseNumber();
        this.subpoint = exercise.getSubpoint();
        this.approvedStudent = exercise.getApprovedStudent();
        this.approvedStudentsPoints = points;
    }
}
