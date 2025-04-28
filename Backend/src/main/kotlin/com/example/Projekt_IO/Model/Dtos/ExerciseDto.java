package com.example.Projekt_IO.Model.Dtos;

import com.example.Projekt_IO.Model.Entities.Exercise;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ExerciseDto {
    private Long id;
    private LocalDateTime classDate;
    private String groupName;
    private Integer exerciseNumber;
    private String subpoint;
    public ExerciseDto(Exercise exercise){
        this.id=exercise.getId();
        this.classDate = exercise.getLesson().getClassDate();
        this.groupName = exercise.getLesson().getCourse().getName();
        this.exerciseNumber = exercise.getExerciseNumber();
        this.subpoint = exercise.getSubpoint();

    }

    public ExerciseDto(){}
}
