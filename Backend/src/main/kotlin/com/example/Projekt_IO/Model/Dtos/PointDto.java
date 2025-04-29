package com.example.Projekt_IO.Model.Dtos;

import com.example.Projekt_IO.Model.Entities.Point;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class PointDto {
        private UUID id;
        private String student;
        private LessonDescriptionDto lesson;
        private Double activityValue;
        public PointDto(Point point){
            this.id= point.getId();
            this.student = point.getStudent();
            this.lesson = new LessonDescriptionDto(point.getLesson());
            this.activityValue = point.getActivityValue();
        }
}
