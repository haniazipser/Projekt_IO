package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.*;
import com.example.Projekt_IO.Model.Entities.Exercise;
import com.example.Projekt_IO.Model.Entities.ExerciseDeclaration;
import com.example.Projekt_IO.Repositories.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final PointService pointService;
    public List<ExerciseDto> getExercisesForLesson(UUID lessonId) {
        return exerciseRepository.findByLesson_Id(lessonId).stream()
                .map((e -> new ExerciseDto(e))).sorted(Comparator.comparing(ExerciseDto::getExerciseNumber)
                        .thenComparing(ExerciseDto::getSubpoint)).collect(Collectors.toList());
    }

    public Set<ExerciseDto> getAssignedExercisesForLesson(String email, UUID id) {
        return exerciseRepository.findByApprovedStudentAndLesson_Id(email,id).stream().map((e -> new ExerciseDto(e))).collect(Collectors.toSet());
    }

    public void updateExercsie(ExerciseDto exercise) {
        Optional<Exercise> e = exerciseRepository.findById(exercise.getId());
        if (e.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This exercise does not exist");
        }
        e.get().setExerciseNumber(exercise.getExerciseNumber());
        e.get().setSubpoint(exercise.getSubpoint());
    }

    public void addSubpointsToExercise(UUID exerciseId, Integer numberOfSubpoints){

        Optional<Exercise> e = exerciseRepository.findById(exerciseId);
        if (e.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This exercise does not exist");
        }else if (e.get().getApprovedStudent()!= null){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can not modify this exercise");
        } else if (numberOfSubpoints <=0 || numberOfSubpoints >=10){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid number of subpoints");
        }

        Exercise exercise = e.get();

        exercise.setSubpoint("a");
        for (int i = 1; i < numberOfSubpoints; i++){
            char letter = (char) ('a' + i);
            Exercise newExercise = new Exercise();
            newExercise.setExerciseNumber(exercise.getExerciseNumber());
            newExercise.setSubpoint(String.valueOf(letter));
            newExercise.setLesson(exercise.getLesson());
            exerciseRepository.save(newExercise);
        }

    }

    public Set<ExerciseWithPointsDto> getList(UUID lessonId) {
        Set<Exercise> exercises = exerciseRepository.findByLesson_Id(lessonId);
        Set<ExerciseWithPointsDto> exercisesWithPoints = new HashSet<>();
        for (Exercise exercise : exercises){
            List<PointDto>activity = pointService.getUsersActivityInCourse(exercise.getApprovedStudent(), exercise.getLesson().getCourse().getId());
            Double sum = activity.stream().mapToDouble(PointDto::getActivityValue).sum();
            exercisesWithPoints.add(new ExerciseWithPointsDto(exercise,sum));
        }
        return exercisesWithPoints;
    }

}
