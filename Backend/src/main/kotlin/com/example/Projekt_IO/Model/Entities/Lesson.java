package com.example.Projekt_IO.Model.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "lesson")
public class Lesson {
    @Id

    private UUID id;
    private LocalDateTime classDate;
    @ManyToOne
    private Course course;
    @OneToMany(mappedBy = "lesson")
    private List<Exercise> lessonExercises;

    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    public Lesson(){}

}
