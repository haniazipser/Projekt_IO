package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.LessonDto;
import com.example.Projekt_IO.Model.Dtos.ExerciseDto;
import com.example.Projekt_IO.Model.Entities.Lesson;
import com.example.Projekt_IO.Model.Entities.Exercise;
import com.example.Projekt_IO.Repositories.LessonRepository;
import com.example.Projekt_IO.Repositories.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ExerciseRepository exerciseRepository;

    public void addExerciseToLesson(Long lessonId, ExerciseDto exerciseDto) {
        Optional<Lesson> classSession = lessonRepository.findById(lessonId);
        if (classSession.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Class session not found");
        }
        Exercise exercise = new Exercise();
        exercise.setExerciseNumber(exerciseDto.getExerciseNumber());
        exercise.setSubpoint(exerciseDto.getSubpoint());
        exercise.setLesson(classSession.get());
        exerciseRepository.save(exercise);

    }

    public LessonDto getNextLesson(Long courseId){
        Optional<Lesson> lesson = lessonRepository.findTopByCourse_IdAndClassDateAfterOrderByClassDateAsc(courseId, LocalDateTime.now());
        if (lesson.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "There are no future lessons added for this group");
        }
        return new LessonDto(lesson.get());
    }
}
