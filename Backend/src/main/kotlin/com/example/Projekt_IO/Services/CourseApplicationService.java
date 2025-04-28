package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.CourseDto;
import com.example.Projekt_IO.Model.Dtos.LessonDto;
import com.example.Projekt_IO.Model.Dtos.ExerciseDto;
import com.example.Projekt_IO.Model.Dtos.TaskDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseApplicationService {
    private final CourseService groupClassService;
    private final EmailService emailService;
    private final LessonService lessonService;
    private final DeclarationService declarationService;
    private final ExerciseService exerciseService;

    public void addStudentToGroup(String email, Long groupId) {
        String link = "http://localhost:8080/group/accept?groupId=" + groupId;
        groupClassService.addStudentToGroup(email, groupId);
        CourseDto group = groupClassService.getGroupInfo(groupId);
        StringBuilder builder = new StringBuilder();
        builder.append("You have been added to ").append(group.getName()).append(" group");
        builder.append('\n').append(link);
        emailService.sendMessage(email,"New group Alert", builder.toString());
    }

    public Set<TaskDto> getStudentsTasks(String email){
        Set<CourseDto> groups = groupClassService.getUsersGroups(email);
        Set<TaskDto> tasks = new HashSet<>();
        for (CourseDto g : groups)   {
            LessonDto s = lessonService.getNextSession(g.getId());
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
