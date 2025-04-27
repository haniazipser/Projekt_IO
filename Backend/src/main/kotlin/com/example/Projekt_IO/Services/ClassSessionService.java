package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Controllers.ClassSessionController;
import com.example.Projekt_IO.Model.Dtos.ExerciseDto;
import com.example.Projekt_IO.Model.Entities.ClassSession;
import com.example.Projekt_IO.Model.Entities.Exercise;
import com.example.Projekt_IO.Repositories.ClassSessionRepository;
import com.example.Projekt_IO.Repositories.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassSessionService {
    private final ClassSessionRepository classSessionRepository;
    private final ExerciseRepository exerciseRepository;

    public void addExerciseToSession(Long sessionId, ExerciseDto exerciseDto) {
        Optional<ClassSession> classSession = classSessionRepository.findById(sessionId);
        if (classSession.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Class session not found");
        }
        Exercise exercise = new Exercise();
        exercise.setExerciseNumber(exerciseDto.getExerciseNumber());
        exercise.setSubpoint(exerciseDto.getSubpoint());
        exercise.setClassSession(classSession.get());
        exerciseRepository.save(exercise);

    }
}
