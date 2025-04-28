package com.example.Projekt_IO.Model.Dtos;

import com.example.Projekt_IO.Model.Entities.Lesson;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class LessonDto {
    private Long id;
    private LocalDateTime classDate;
    private String CourseName;

    public LessonDto(Lesson lesson){
        this.id = lesson.getId();
        this.classDate = lesson.getClassDate();
        this.CourseName = lesson.getCourse().getName();
    }
}
