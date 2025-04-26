package com.example.Projekt_IO.Controllers;

import com.example.Projekt_IO.Model.Dtos.ExerciseDto;
import com.example.Projekt_IO.Services.ClassSessionService;
import com.example.Projekt_IO.Services.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ClassSessionController {
    private final ExerciseService exerciseService;
    private final ClassSessionService classSessionService;
    @GetMapping("/{sessionId}/exercises")
    public Set<ExerciseDto> getExercisesForSession (@PathVariable Long sessionId){
        return exerciseService.getExercisesForSession(sessionId);
    }

    @PostMapping("/{sessionId}/addExercise")
    public void addExerciseToSession(@PathVariable Long sessionId, @RequestBody ExerciseDto exercise){
        classSessionService.addExerciseToSession(sessionId,exercise);
    }
}
