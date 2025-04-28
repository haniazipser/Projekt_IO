package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.PointDto;
import com.example.Projekt_IO.Model.Entities.Point;
import com.example.Projekt_IO.Model.Entities.Exercise;
import com.example.Projekt_IO.Repositories.PointRepository;
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
public class PointService {
    private final PointRepository pointRepository;
    private final ExerciseRepository exerciseRepository;

    public Set<PointDto> getUsersActivity (String email){
        return pointRepository.findByStudent(email).stream().map(a -> new PointDto(a)).collect(Collectors.toSet());
    }
    public Set<PointDto> getUsersActivityInGroupClass(String email, Long groupId) {
        return pointRepository.findByStudentAndSource_Id(email, groupId).stream().map(a -> new PointDto(a)).collect(Collectors.toSet());
    }

    public void addStudentActivity(String email, PointDto activity) {
        Point newPoint = new Point();
        newPoint.setActivityValue(activity.getActivityValue());
        newPoint.setStudent(email);
        Optional<Exercise> exercise = exerciseRepository.findById(activity.getSource().getId());
        if (exercise.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exerise not found");
        }
        newPoint.setSource(exercise.get());
        pointRepository.save(newPoint);
    }
}
