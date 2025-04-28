package com.example.Projekt_IO.Model.Entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

@Embeddable
@Getter @Setter
public class LessonTime {
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private LocalTime time;
    public LessonTime(){};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonTime that = (LessonTime) o;
        return dayOfWeek == that.dayOfWeek &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfWeek, time);
    }

}