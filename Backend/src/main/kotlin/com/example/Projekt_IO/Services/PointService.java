package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.PointDto;
import com.example.Projekt_IO.Model.Entities.Lesson;
import com.example.Projekt_IO.Model.Entities.Point;
import com.example.Projekt_IO.Model.Entities.Exercise;
import com.example.Projekt_IO.Repositories.LessonRepository;
import com.example.Projekt_IO.Repositories.PointRepository;
import com.example.Projekt_IO.Repositories.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;
    private final LessonRepository lessonRepository;

    public Set<PointDto> getUsersActivity (String email){
        return pointRepository.findByStudent(email).stream().map(a -> new PointDto(a)).collect(Collectors.toSet());
    }
    public List<PointDto> getUsersActivityInCourse(String email, UUID courseId) {
        return pointRepository.findByStudentAndLesson_Course_Id(email, courseId).stream()
                .sorted(Comparator.comparing(p -> p.getLesson().getClassDate()))
                .map(a -> new PointDto(a)).collect(Collectors.toList());
    }

    public void addStudentActivity(String email, PointDto activity) {
        Optional<Point> p = pointRepository.findById(activity.getId());
        if(p.isEmpty()) {
            Point newPoint = new Point();
            newPoint.setActivityValue(activity.getActivityValue());
            newPoint.setStudent(email);
            Optional<Lesson> lesson = lessonRepository.findById(activity.getLesson().getId());
            if (lesson.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found");
            }
            newPoint.setLesson(lesson.get());
            pointRepository.save(newPoint);
        }else{
            Point point = p.get();
            point.setActivityValue(activity.getActivityValue());
            pointRepository.save(point);
        }

    }
}
