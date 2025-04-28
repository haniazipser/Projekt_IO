package com.example.Projekt_IO.Model.Dtos;

import com.example.Projekt_IO.Model.Entities.Course;
import com.example.Projekt_IO.Model.Entities.LessonTime;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter @Setter
public class CourseDto {
    private Long id;
    private String name;
    private String  instructor;
    private String creator;
    private Set<LessonTime> lessonTimes;
    private Set<LessonDto> lessons;
    public CourseDto(Course group){
        this.id = group.getId();
        this.name = group.getName();
        this.instructor = group.getInstructor();
        this.creator = group.getCreator();
        this.lessonTimes = group.getLessonTimes();
        this.lessons = group.getLessons().stream().map(s -> new LessonDto(s)).collect(Collectors.toSet());
    }


}
