package com.example.Projekt_IO.Model.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String student;
    @ManyToOne
    private Exercise source;
    private Double activityValue;
    public Activity(){};

}
