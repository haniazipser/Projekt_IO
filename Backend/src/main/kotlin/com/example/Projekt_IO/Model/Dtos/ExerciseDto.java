package com.example.Projekt_IO.Model.Dtos;

import com.example.Projekt_IO.Model.Entities.Exercise;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
public class ExerciseDto {
    private UUID id;
    private Instant classDate;
    private String groupName;
    private Integer exerciseNumber;
    private String subpoint;
    private String approvedStudent;
    public ExerciseDto(Exercise exercise){
        this.id=exercise.getId();
        this.classDate = exercise.getLesson().getClassDate();
        this.groupName = exercise.getLesson().getCourse().getName();
        this.exerciseNumber = exercise.getExerciseNumber();
        this.subpoint = exercise.getSubpoint();
        this.approvedStudent = exercise.getApprovedStudent();

    }

    public ExerciseDto(){}
}
