package com.example.Projekt_IO.Controllers;

import com.example.Projekt_IO.Model.Dtos.ExerciseDto;
import com.example.Projekt_IO.Services.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseService exerciseService;
   /* @PutMapping("")
    public void updateExercise (@RequestBody ExerciseDto exercise){
        exerciseService.updateExercsie(exercise);
    }

    @PostMapping("/{exerciseId}/{numberOfSubpoints}")
    public void addSubpointsToExercise (@PathVariable UUID exerciseId, @PathVariable Integer numberOfSubpoints){
        exerciseService.addSubpointsToExercise(exerciseId, numberOfSubpoints);
    }*/
}
