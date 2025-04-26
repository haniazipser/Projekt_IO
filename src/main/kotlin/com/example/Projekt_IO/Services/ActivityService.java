package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.ActivityDto;
import com.example.Projekt_IO.Model.Entities.Activity;
import com.example.Projekt_IO.Repositories.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;

    public Set<ActivityDto> getUsersActivity (String email){
        return activityRepository.findByStudent(email).stream().map(a -> new ActivityDto(a)).collect(Collectors.toSet());
    }
    public Set<ActivityDto> getUsersActivityInGroupClass(String email, Long groupId) {
        return activityRepository.findByStudentAndSource_Id(email, groupId).stream().map(a -> new ActivityDto(a)).collect(Collectors.toSet());
    }
}
