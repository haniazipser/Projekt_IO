package com.example.Projekt_IO.Model.Dtos;

import com.example.Projekt_IO.Model.Entities.ClassGroup;
import com.example.Projekt_IO.Model.Entities.ClassSession;
import com.example.Projekt_IO.Model.Entities.Exercise;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
@Getter @Setter
public class ClassSessionDto {
    private Long id;
    private LocalDateTime classDate;
    private String classGroupName;

    public ClassSessionDto(ClassSession classSession){
        this.id = classSession.getId();
        this.classDate = classSession.getClassDate();
        this.classGroupName = classSession.getClassGroup().getName();
    }
}
