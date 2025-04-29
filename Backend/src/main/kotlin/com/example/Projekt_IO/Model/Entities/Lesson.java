package com.example.Projekt_IO.Model.Entities;

import com.example.Projekt_IO.Model.Dtos.LessonStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
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
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercise> lessonExercises;

    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    public Lesson(){}

    public LessonStatus getLessonStatus() {
        if (classDate.isBefore(LocalDateTime.now())){
            return LessonStatus.PAST;
        } if ( Duration.between(LocalDateTime.now(), classDate).toDays() <= 7){
            return LessonStatus.NEAR;
        } else {
            return LessonStatus.FUTURE;
        }
    }
}
