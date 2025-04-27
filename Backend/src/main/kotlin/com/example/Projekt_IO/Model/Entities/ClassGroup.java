package com.example.Projekt_IO.Model.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.Set;
@Getter @Setter
@Entity
@Table(name = "class_group")
public class ClassGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String  instructor;
    private String creator;
    @ElementCollection
    private Set<ClassTime> classTimes;

    @OneToMany(mappedBy = "classGroup")
    private Set<ClassSession> sessions;

    //private Set<String> students;
    @OneToMany(mappedBy = "classGroup")
    private Set<StudentGroup> students;


    public ClassGroup(){}

}
