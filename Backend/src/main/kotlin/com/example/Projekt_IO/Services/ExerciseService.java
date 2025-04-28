package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.ExerciseDto;
import com.example.Projekt_IO.Repositories.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    public Set<ExerciseDto> getExercisesForLesson(Long lessonId) {
        return exerciseRepository.findByLesson_Id(lessonId).stream().map((e -> new ExerciseDto(e))).collect(Collectors.toSet());
    }

    public Set<ExerciseDto> getAssignedExercisesForLesson(String email, Long id) {
        return exerciseRepository.findByApprovedStudentAndLesson_Id(email,id).stream().map((e -> new ExerciseDto(e))).collect(Collectors.toSet());
    }
}
