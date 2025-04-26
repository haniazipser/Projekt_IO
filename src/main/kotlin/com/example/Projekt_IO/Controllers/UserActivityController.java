package com.example.Projekt_IO.Controllers;

import com.example.Projekt_IO.Services.ActivityService;
import com.example.Projekt_IO.Model.Dtos.ActivityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UserActivityController {
    private final ActivityService activityService;
    @GetMapping("/activity/{email}")
    public Set<ActivityDto> getUsersActivity(@PathVariable String email){
        return activityService.getUsersActivity(email);
    }

    @GetMapping("/activity/{email}/{groupId}")
    public Set<ActivityDto> getUsersActivityInGroupClass(@PathVariable String email, @PathVariable Long groupId){
        return activityService.getUsersActivityInGroupClass(email,groupId);
    }

}
