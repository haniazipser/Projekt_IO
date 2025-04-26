package com.example.Projekt_IO.Model.Entities;

import jakarta.persistence.*;

@Entity
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String email;
    @ManyToOne
    private ClassGroup classGroup;
}
