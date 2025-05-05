package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.CourseDto;
import com.example.Projekt_IO.Model.Dtos.DeclarationDto;
import com.example.Projekt_IO.Model.Dtos.ExerciseDto;
import com.example.Projekt_IO.Model.Dtos.LessonDto;
import com.itextpdf.text.DocumentException;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ExerciseApplicationService {
    private final EmailService emailService;
    private final FileService fileService;
    private final ExerciseService exerciseService;
    private final LessonService lessonService;
    public void exportListToPdf(UUID lessonId) {
        Set<ExerciseDto> exercises = exerciseService.getList(lessonId);
        LessonDto lesson = lessonService.getLessonInfo(lessonId);
        try {
            fileService.createPDFList(exercises, lesson);
        }catch (DocumentException | FileNotFoundException e){
            System.out.println("error");
        }

        try {
            emailService.sendMessageWithList("haniazipser2004@gmail.com");
        }catch (MessagingException | FileNotFoundException e){
            System.out.println("error");
        }
    }
}
