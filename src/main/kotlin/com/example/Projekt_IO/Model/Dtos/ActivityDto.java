package com.example.Projekt_IO.Model.Dtos;

import com.example.Projekt_IO.Model.Entities.Activity;
import com.example.Projekt_IO.Model.Entities.Exercise;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ActivityDto {
        private Long id;
        private String student;
        private ExerciseDto source;
        private Double activityValue;

        public ActivityDto(Activity activity){
            this.id=activity.getId();
            this.student = activity.getStudent();
            this.source = new ExerciseDto(activity.getSource());
            this.activityValue = activity.getActivityValue();
        }
}
