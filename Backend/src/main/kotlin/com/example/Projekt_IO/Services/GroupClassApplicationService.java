package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.ClassGroupDto;
import com.example.Projekt_IO.Model.Dtos.ClassSessionDto;
import com.example.Projekt_IO.Model.Dtos.ExerciseDto;
import com.example.Projekt_IO.Model.Dtos.TaskDto;
import com.example.Projekt_IO.Model.Entities.ClassGroup;
import com.example.Projekt_IO.Model.Entities.ClassSession;
import com.example.Projekt_IO.Model.Entities.Exercise;
import com.example.Projekt_IO.Repositories.ExerciseRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupClassApplicationService {
    private final GroupClassService groupClassService;
    private final EmailService emailService;
    private final ClassSessionService classSessionService;
    private final DeclarationService declarationService;
    private final ExerciseService exerciseService;

    public void addStudentToGroup(String email, Long groupId) {
        groupClassService.addStudentToGroup(email, groupId);
        ClassGroupDto group = groupClassService.getGroupInfo(groupId);
        StringBuilder builder = new StringBuilder();
        builder.append("You have been added to ").append(group.getName()).append(" group");
        emailService.sendMessage(email,"New group Alert", builder.toString());
    }

    public Set<TaskDto> getStudentsTasks(String email){
        Set<ClassGroupDto> groups = groupClassService.getUsersGroups(email);
        Set<TaskDto> tasks = new HashSet<>();
        for (ClassGroupDto g : groups)   {
            ClassSessionDto s = classSessionService.getNextSession(g.getId());
            TaskDto task = new TaskDto();
            task.setGroupName(g.getName());
            task.setGroupId(g.getId());
            task.setDueDate(s.getClassDate());
            Integer count = declarationService.getDeclarationsForSessionCount(email, s.getId());
            task.setNumberOfDeclarations(count);
            Set<ExerciseDto> assigned = exerciseService.getAssignedExercisesForSession(email, s.getId());
            task.setAssigned(assigned);
            tasks.add(task);
        }
        return tasks;
    }
}
