package com.example.Projekt_IO.Model.Dtos;

import com.example.Projekt_IO.Model.Entities.LessonTime;
import jakarta.validation.constraints.Email;
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
    @Email
    private String  instructor;
    private Set<LessonTime> lessonTimes;
    private Set<String> students;
    private Instant endDate;
    private Instant startDate;
    private Integer frequency;
    public NewCourseDto(){}
}
