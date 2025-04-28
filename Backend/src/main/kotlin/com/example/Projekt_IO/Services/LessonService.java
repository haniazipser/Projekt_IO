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

    public void addExerciseToSession(Long sessionId, ExerciseDto exerciseDto) {
        Optional<Lesson> classSession = lessonRepository.findById(sessionId);
        if (classSession.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Class session not found");
        }
        Exercise exercise = new Exercise();
        exercise.setExerciseNumber(exerciseDto.getExerciseNumber());
        exercise.setSubpoint(exerciseDto.getSubpoint());
        exercise.setLesson(classSession.get());
        exerciseRepository.save(exercise);

    }

    public LessonDto getNextSession(Long groupId){
        Optional<Lesson> session = lessonRepository.findTopByCourse_IdAndClassDateAfterOrderByClassDateAsc(groupId, LocalDateTime.now());
        if (session.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "There are no future sessions added for thsi group");
        }
        return new LessonDto(session.get());
    }
}
