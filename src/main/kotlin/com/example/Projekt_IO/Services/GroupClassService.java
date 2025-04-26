package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.ClassGroupDto;
import com.example.Projekt_IO.Model.Dtos.NewGroupDto;
import com.example.Projekt_IO.Model.Entities.ClassGroup;

import com.example.Projekt_IO.Repositories.ClassGroupRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupClassService {
    private final ClassGroupRepository classGroupRepository;
    public Set<ClassGroupDto> getUsersGroups (String email){
      return classGroupRepository.findDistinctByStudents_Email(email).stream().map(g -> new ClassGroupDto(g)).collect(Collectors.toSet());
    }

    public void createGroup(String email, NewGroupDto groupDto) {
        ClassGroup classGroup = new ClassGroup();
        classGroup.setCreator(email);
        classGroup.setClassTimes(groupDto.getClassTimes());
        classGroup.setName(groupDto.getName());
        classGroup.setInstructor(groupDto.getInstructor());
        classGroupRepository.save(classGroup);
    }
}
