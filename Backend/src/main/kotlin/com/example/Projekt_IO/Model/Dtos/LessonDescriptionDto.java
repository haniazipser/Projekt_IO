package com.example.Projekt_IO.Model.Dtos;

import com.example.Projekt_IO.Model.Entities.Lesson;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Getter @Setter
public class LessonDescriptionDto {
    private UUID id;
    private Instant classDate;
    private String courseName;
    private LessonStatus status;
    public LessonDescriptionDto(Lesson lesson){
        this.id = lesson.getId();
        this.classDate = lesson.getClassDate();
        this.courseName = lesson.getCourse().getName();
        this.status = lesson.getLessonStatus();
    }
}
