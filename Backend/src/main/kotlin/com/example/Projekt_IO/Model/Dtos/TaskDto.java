package com.example.Projekt_IO.Model.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class TaskDto {
    private UUID groupId;
    private String courseName;
    private Instant dueDate;
    private Integer numberOfDeclarations;
    private Set<ExerciseDto> assigned;
}
