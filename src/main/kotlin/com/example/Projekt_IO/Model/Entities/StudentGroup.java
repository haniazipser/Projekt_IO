package com.example.Projekt_IO.Model.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String email;
    @ManyToOne
    private ClassGroup classGroup;
}
