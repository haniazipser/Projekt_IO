package com.example.Projekt_IO.Controllers;

import com.example.Projekt_IO.Model.Dtos.ExerciseDto;
import com.example.Projekt_IO.Services.LessonService;
import com.example.Projekt_IO.Services.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class LessonController {
    private final ExerciseService exerciseService;
    private final LessonService lessonService;
    @GetMapping("/{lessonId}/exercises")
    public Set<ExerciseDto> getExercisesForLesson(@PathVariable Long lessonId){
        return exerciseService.getExercisesForLesson(lessonId);
    }

    @PostMapping("/{lessonId}/addExercise")
    public void addExerciseToLesson(@PathVariable Long lessonId, @RequestBody ExerciseDto exercise){
        lessonService.addExerciseToLesson(lessonId,exercise);
    }
}
