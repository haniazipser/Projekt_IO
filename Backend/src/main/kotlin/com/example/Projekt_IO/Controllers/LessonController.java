package com.example.Projekt_IO.Controllers;

import com.example.Projekt_IO.Model.Dtos.ExerciseDto;
import com.example.Projekt_IO.Model.Dtos.LessonDto;
import com.example.Projekt_IO.Services.LessonService;
import com.example.Projekt_IO.Services.ExerciseService;
import com.example.Projekt_IO.Services.UserInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson")
public class LessonController {
    private final ExerciseService exerciseService;
    private final LessonService lessonService;
    private final UserInfoService userInfoService;
    @GetMapping("/{lessonId}/exercises")
    public ResponseEntity<List<ExerciseDto>> getExercisesForLesson(@PathVariable UUID lessonId){
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
                .body(exerciseService.getExercisesForLesson(lessonId));
    }

    @DeleteMapping("/{lessonId}")
    public void deleteLesson(@PathVariable UUID lessonId){
        lessonService.deleteLesson(lessonId);
    }

    @GetMapping("/{courseId}/lessons")
    public ResponseEntity<List<LessonDto>> getLessonsForCourse (@PathVariable UUID courseId){
        String email = userInfoService.getLoggedUserInfo().getEmail();
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.DAYS))
                .body(lessonService.getLessonsForCourse(courseId, email));
    }

   @PutMapping("/exercises")
    public void updateExercisesForLesson(@Valid @RequestBody LessonDto lesson){
        lessonService.updateExercisesForLesson(lesson);
    }

    @PutMapping("{courseId}/{date}/addLesson")
    public LessonDto addLesson(@PathVariable UUID courseId, @PathVariable Instant date){
        return lessonService.addNewLesson(courseId,date);
    }
}
