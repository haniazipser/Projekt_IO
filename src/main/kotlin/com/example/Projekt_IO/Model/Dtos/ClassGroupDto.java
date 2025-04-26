package com.example.Projekt_IO.Model.Dtos;

import com.example.Projekt_IO.Model.Entities.ClassGroup;
import com.example.Projekt_IO.Model.Entities.ClassSession;
import com.example.Projekt_IO.Model.Entities.ClassTime;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter @Setter
public class ClassGroupDto {
    private Long id;
    private String name;
    private String  instructor;
    private String creator;
    private Set<ClassTime> classTimes;
    private Set<ClassSessionDto> sessions;
    public ClassGroupDto(ClassGroup group){
        this.id = group.getId();
        this.name = group.getName();
        this.instructor = group.getInstructor();
        this.creator = group.getCreator();
        this.classTimes = group.getClassTimes();
        this.sessions = group.getSessions().stream().map(s -> new ClassSessionDto(s)).collect(Collectors.toSet());
    }


}
