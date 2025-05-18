package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.*;
import com.itextpdf.text.DocumentException;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExerciseApplicationService {
    private final EmailService emailService;
    private final FileService fileService;
    private final ExerciseService exerciseService;
    private final LessonService lessonService;
    public void exportListToPdf(UUID lessonId) {
        List<ExerciseWithPointsDto> exercises1 = exerciseService.getList(lessonId)
                .stream().sorted(Comparator.comparing(ExerciseWithPointsDto::getExerciseNumber).thenComparing(ExerciseWithPointsDto::getSubpoint))
                .collect(Collectors.toList());

        List<ExerciseWithPointsDto> exercises2 = exerciseService.getList(lessonId)
                .stream().sorted(Comparator.comparing(ExerciseWithPointsDto::getApprovedStudentsPoints))
                .collect(Collectors.toList());

        LessonDescriptionDto lesson = lessonService.getLessonInfo(lessonId);
        String name;
        try {
            name = fileService.createPDFList(exercises1,exercises2, lesson);
            emailService.sendMessageWithList(lesson.getInstructor(), lesson, name);
            try {
                Files.delete(Path.of(name));
            } catch (IOException e) {
                System.err.println("Nie udało się usunąć pliku: " + name);
                e.printStackTrace();
            }
        }catch (DocumentException | FileNotFoundException | MessagingException e){
            e.printStackTrace();
        }

    }
}
