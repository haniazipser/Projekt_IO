package com.example.Projekt_IO.Model.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter @Setter
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String  instructor;
    private String creator;
    @ElementCollection
    private Set<LessonTime> lessonTimes;

    @OneToMany(mappedBy = "course")
    private Set<Lesson> lessons;

    //private Set<String> students;
    @OneToMany(mappedBy = "course")
    private Set<Participant> students;


    public Course(){}

}
