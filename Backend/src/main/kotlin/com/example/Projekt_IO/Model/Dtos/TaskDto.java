package com.example.Projekt_IO.Model.Dtos;

import com.example.Projekt_IO.Model.Entities.ClassTime;
import com.example.Projekt_IO.Model.Entities.Exercise;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
@Getter
@Setter
public class TaskDto {
    private Long groupId;
    private String groupName;
    private LocalDateTime dueDate;
    private Integer numberOfDeclarations;
    private Set<ExerciseDto> assigned;

}
