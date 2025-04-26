package com.example.Projekt_IO.Model.Dtos;

import com.example.Projekt_IO.Model.Entities.ClassTime;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class NewGroupDto {
    private Long id;
    private String name;
    private String  instructor;
    private String creator;
    private Set<ClassTime> classTimes;

    public NewGroupDto(){}
}
