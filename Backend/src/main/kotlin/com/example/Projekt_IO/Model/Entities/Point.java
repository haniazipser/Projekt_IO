package com.example.Projekt_IO.Model.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "point")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String student;
    @ManyToOne
    private Exercise source;
    private Double activityValue;//jesli zmieniam nazwe tutaj
    public Point(){};

}
