package com.example.Projekt_IO.Controllers;

import com.example.Projekt_IO.Services.ActivityService;
import com.example.Projekt_IO.Model.Dtos.ActivityDto;
import com.example.Projekt_IO.Services.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activity")
public class UserActivityController {
    private final ActivityService activityService;
    private final UserInfoService userInfoService;
    @GetMapping("")
    public Set<ActivityDto> getUsersActivity(){
        String email = userInfoService.getLoggedUserInfo().getEmail();
        return activityService.getUsersActivity(email);
    }

    @GetMapping("/{groupId}")
    public Set<ActivityDto> getUsersActivityInGroupClass( @PathVariable Long groupId){
        String email = userInfoService.getLoggedUserInfo().getEmail();
        return activityService.getUsersActivityInGroupClass(email,groupId);
    }

    @PostMapping("")//wysylasz mi z formularza obiekt z polami jak ma activity dto
    public void addStudentActivity( @RequestBody ActivityDto activity){
        String email = userInfoService.getLoggedUserInfo().getEmail();
        activityService.addStudentActivity(email,activity);
    }

}
