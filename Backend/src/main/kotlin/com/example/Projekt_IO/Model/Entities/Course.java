package com.example.Projekt_IO.Model.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;
import java.util.UUID;

@Getter @Setter
@Entity
@Table(name = "course")
public class Course {
    @Id
    private UUID id;
    private String name;
    private String  instructor;
    private String creator;
    @ElementCollection
    private Set<LessonTime> lessonTimes;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Lesson> lessons;

    //private Set<String> students;
    @OneToMany(mappedBy = "course")
    private Set<Participant> students;
    private Instant startDate;

    private Instant endDate;
    private Integer frequency;
    private Boolean isArchived;

    private String courseCode;

    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }


    public Course(){}

    public Instant getFirstLesson(LessonTime lessonTime) {

        ZoneId zone = ZoneId.systemDefault();

        LocalDate date = startDate.atZone(zone).toLocalDate();

        LocalDate nextLessonDate = date.with(TemporalAdjusters.next(lessonTime.getDayOfWeek()));

        Instant nextLessonInstant = nextLessonDate.atStartOfDay(zone).toInstant();

        return nextLessonInstant;
    }

    public Instant calculateNextOccurrence(LessonTime lessonTime) {
        return Instant.now().with(TemporalAdjusters.next(lessonTime.getDayOfWeek()));
    }


    public Instant findNextLessonDate() {
        return lessonTimes.stream()//dla kazdego dnia nastepne wystapienie
                .map(classTime -> calculateNextOccurrence(classTime))
                .min(Instant::compareTo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT,
                        "Calendar for this course is empty"
                ));
    }

    public boolean isStudentAMemeber(String email){
        boolean found = false;
        for (Participant student : students){
            if (student.getEmail().toLowerCase().equals(email)){
                found = true;
                break;
            }
        }
        return found;
    }

}
