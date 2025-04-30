package com.example.Projekt_IO.Model.Entities;

import com.example.Projekt_IO.Model.DeclarationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "exercise_declaration")
public class ExerciseDeclaration {
    @Id
    private UUID id;
    private Instant declarationDate;

    @Enumerated(EnumType.STRING)
    private DeclarationStatus declarationStatus;

    @ManyToOne
    private Exercise exercise;

    private String student;

    public ExerciseDeclaration(){};
    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
