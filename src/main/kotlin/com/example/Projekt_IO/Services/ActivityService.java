package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.ActivityDto;
import com.example.Projekt_IO.Model.Entities.Activity;
import com.example.Projekt_IO.Model.Entities.Exercise;
import com.example.Projekt_IO.Repositories.ActivityRepository;
import com.example.Projekt_IO.Repositories.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final ExerciseRepository exerciseRepository;

    public Set<ActivityDto> getUsersActivity (String email){
        return activityRepository.findByStudent(email).stream().map(a -> new ActivityDto(a)).collect(Collectors.toSet());
    }
    public Set<ActivityDto> getUsersActivityInGroupClass(String email, Long groupId) {
        return activityRepository.findByStudentAndSource_Id(email, groupId).stream().map(a -> new ActivityDto(a)).collect(Collectors.toSet());
    }

    public void addStudentActivity(String email, ActivityDto activity) {
        Activity newActivity = new Activity();
        newActivity.setActivityValue(activity.getActivityValue());
        newActivity.setStudent(email);
        Optional<Exercise> exercise = exerciseRepository.findById(activity.getSource().getId());
        if (exercise.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exerise not found");
        }
        newActivity.setSource(exercise.get());
        activityRepository.save(newActivity);
    }
}
