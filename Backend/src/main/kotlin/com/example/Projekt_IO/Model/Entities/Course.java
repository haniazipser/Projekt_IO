package com.example.Projekt_IO.Model.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @OneToMany(mappedBy = "course")
    private Set<Lesson> lessons;

    //private Set<String> students;
    @OneToMany(mappedBy = "course")
    private Set<Participant> students;

    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }


    public Course(){}

    public LocalDateTime calculateNextOccurrence(LessonTime lessonTime, LocalDateTime now) {
        DayOfWeek targetDay = lessonTime.getDayOfWeek();
        LocalTime targetTime = lessonTime.getTime();

        DayOfWeek currentDay = now.getDayOfWeek();
        LocalDate currentDate = now.toLocalDate();

        int daysDifference = targetDay.getValue() - currentDay.getValue();

        if (daysDifference < 0 || (daysDifference == 0 && now.toLocalTime().isAfter(targetTime))) {
            daysDifference += 7;
        }

        LocalDate nextDate = currentDate.plusDays(daysDifference);
        return LocalDateTime.of(nextDate, targetTime);
    }

    public LocalDateTime findNextLessonDate() {
        LocalDateTime now = LocalDateTime.now();
        return lessonTimes.stream()
                .map(classTime -> calculateNextOccurrence(classTime, now))
                .min(LocalDateTime::compareTo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT,
                        "Calendar for this sourse is empty"
                ));
    }

    public boolean isStudenAMemebr(String email){
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
