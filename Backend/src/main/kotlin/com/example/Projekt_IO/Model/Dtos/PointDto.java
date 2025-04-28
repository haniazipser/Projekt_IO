package com.example.Projekt_IO.Model.Dtos;

import com.example.Projekt_IO.Model.Entities.Point;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PointDto {
        private Long id;
        private String student;
        private ExerciseDto source;
        private Double activityValue;//zmieniam tutaj

        public PointDto(Point point){
            this.id= point.getId();
            this.student = point.getStudent();
            this.source = new ExerciseDto(point.getSource());
            this.activityValue = point.getActivityValue();//i tutaj
        }
}
