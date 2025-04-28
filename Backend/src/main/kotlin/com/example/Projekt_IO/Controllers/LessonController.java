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
    @GetMapping("/{sessionId}/exercises")
    public Set<ExerciseDto> getExercisesForSession (@PathVariable Long sessionId){
        return exerciseService.getExercisesForSession(sessionId);
    }

    @PostMapping("/{sessionId}/addExercise")
    public void addExerciseToSession(@PathVariable Long sessionId, @RequestBody ExerciseDto exercise){
        lessonService.addExerciseToSession(sessionId,exercise);
    }
}
