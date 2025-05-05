package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.CourseDto;
import com.example.Projekt_IO.Model.Dtos.LessonDto;
import com.example.Projekt_IO.Model.Dtos.ExerciseDto;
import com.example.Projekt_IO.Model.Dtos.TaskDto;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseApplicationService {
    private final CourseService groupClassService;
    private final EmailService emailService;
    private final LessonService lessonService;
    private final DeclarationService declarationService;
    private final ExerciseService exerciseService;

    public void addStudentToGroup( String email, UUID courseId) {

        String link = "tutti://group/" + courseId;
        groupClassService.addStudentToGroup(email, courseId);
        CourseDto group = groupClassService.getGroupInfo(courseId);

        String message = "<p>You have been added to <b>" + group.getName() + "</b> course!</p>" +
                "<p>If you accept the invitation, use this code:</p>" +
                "<div style='margin-top: 15px; margin-bottom: 15px; font-weight: bold; font-size: 18px;'>" +
                group.getCourseCode() +
                "</div>" +
                "<p>in your <b>ePolan</b> application to join the team.</p>";
        try {
            emailService.sendMessage(email, "New group Alert", message);
        }catch (MessagingException e){
            System.out.println("error");
        }
    }

    public Set<TaskDto> getStudentsTasks(String email){
        Set<CourseDto> groups = groupClassService.getUsersGroups(email);
        Set<TaskDto> tasks = new HashSet<>();
        for (CourseDto g : groups)   {
            LessonDto s = lessonService.getNextLesson(g.getId());
            TaskDto task = new TaskDto();
            task.setCourseName(g.getName());
            task.setGroupId(g.getId());
            task.setDueDate(s.getClassDate());
            Integer count = declarationService.getDeclarationsForSessionCount(email, s.getId());
            task.setNumberOfDeclarations(count);
            Set<ExerciseDto> assigned = exerciseService.getAssignedExercisesForLesson(email, s.getId());
            task.setAssigned(assigned);
            tasks.add(task);
        }
        return tasks;
    }
}
