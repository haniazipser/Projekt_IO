package com.example.Projekt_IO.Model.Dtos;

import com.example.Projekt_IO.Model.Entities.LessonTime;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class NewCourseDto {

    private UUID id;
    private String name;
    private String  instructor;
    private Set<LessonTime> lessonTimes;
    private Set<String> students;
    private Instant endDate;
    private Instant startDate;
    public NewCourseDto(){}
}
