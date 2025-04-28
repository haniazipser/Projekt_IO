package com.example.Projekt_IO.Controllers;

import com.example.Projekt_IO.Model.Dtos.ExerciseDto;
import com.example.Projekt_IO.Model.Dtos.LessonDto;
import com.example.Projekt_IO.Services.LessonService;
import com.example.Projekt_IO.Services.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class LessonController {
    private final ExerciseService exerciseService;
    private final LessonService lessonService;
    @GetMapping("/{lessonId}/exercises")
    public List<ExerciseDto> getExercisesForLesson(@PathVariable UUID lessonId){
        return exerciseService.getExercisesForLesson(lessonId);
    }

    @PostMapping("/{lessonId}/addExercise")
    public void addExerciseToLesson(@PathVariable UUID lessonId, @RequestBody ExerciseDto exercise){
        lessonService.addExerciseToLesson(lessonId,exercise);
    }

    @PostMapping("/create/{courseId}/{numberOfExercises}")
    public LessonDto createNewLessonForCourse(@PathVariable UUID courseId, @PathVariable Integer numberOfExercises){
        return lessonService.createNewLesson(courseId,numberOfExercises);
    }

    @DeleteMapping("/{lessonId}")
    public void deleteLesson(@PathVariable UUID lessonId){
        lessonService.deleteLesson(lessonId);
    }

}
