package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.ExerciseDto;
import com.example.Projekt_IO.Repositories.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    public List<ExerciseDto> getExercisesForLesson(UUID lessonId) {
        return exerciseRepository.findByLesson_Id(lessonId).stream()
                .map((e -> new ExerciseDto(e))).sorted(Comparator.comparing(ExerciseDto::getExerciseNumber)
                        .thenComparing(ExerciseDto::getSubpoint)).collect(Collectors.toList());
    }

    public Set<ExerciseDto> getAssignedExercisesForLesson(String email, UUID id) {
        return exerciseRepository.findByApprovedStudentAndLesson_Id(email,id).stream().map((e -> new ExerciseDto(e))).collect(Collectors.toSet());
    }
}
