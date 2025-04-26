package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.ClassGroupDto;
import com.example.Projekt_IO.Model.Dtos.NewGroupDto;
import com.example.Projekt_IO.Model.Entities.ClassGroup;

import com.example.Projekt_IO.Model.Entities.StudentGroup;
import com.example.Projekt_IO.Repositories.ClassGroupRepository;

import com.example.Projekt_IO.Repositories.StudentGroupRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupClassService {
    private final ClassGroupRepository classGroupRepository;
    private final StudentGroupRepository studentGroupRepository;
    Logger logger = LoggerFactory.getLogger(GroupClassService.class);
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

    public void addStudentToGroup(String email, Long groupId) {
        Optional<ClassGroup> classGroup = classGroupRepository.findById(groupId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String loggedEmail = jwt.getClaim("email");

        logger.info("name: " + loggedEmail);
        logger.info("creator: " + classGroup.get().getCreator());
        if (classGroup.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
        }else if (!classGroup.get().getCreator().toLowerCase().equals( loggedEmail.toLowerCase())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"You are not authorized to add students to this group");
        }

        for (StudentGroup sg : classGroup.get().getStudents()){
            if (sg.getEmail().equals(email)){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This student is already in this group");
            }
        }

        StudentGroup studentGroup = new StudentGroup();
        studentGroup.setEmail(email);
        studentGroup.setClassGroup(classGroup.get());

        studentGroupRepository.save(studentGroup);
    }

    public void deleteStudentFromGroup(String email, Long groupId) {
        Optional<ClassGroup> classGroup = classGroupRepository.findById(groupId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String loggedEmail = jwt.getClaim("email");
        if (classGroup.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
        }else if (!classGroup.get().getCreator().toLowerCase().equals( loggedEmail.toLowerCase())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"You are not authorized to delete students to this group");
        }
        boolean found = false;
        for (StudentGroup sg : classGroup.get().getStudents()){
            if (sg.getEmail().equals(email)){
                studentGroupRepository.delete(sg);
                found = true;
                break;
            }
        }
        if (!found){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This student is not in this group");
        }



    }

    public Set<String> getStudentsInGroup(Long groupId) {
        Optional<ClassGroup> classGroup = classGroupRepository.findById(groupId);
        if (classGroup.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
        }
        return classGroup.get().getStudents().stream().map(s -> s.getEmail()).collect(Collectors.toSet());
    }

    public ClassGroupDto getGroupInfo(Long groupId){
        ClassGroup classGroup = classGroupRepository.findById(groupId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found"));
        return new ClassGroupDto(classGroup);
    }
}
