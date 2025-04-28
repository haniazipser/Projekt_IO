package com.example.Projekt_IO.Controllers;

import com.example.Projekt_IO.Services.PointService;
import com.example.Projekt_IO.Model.Dtos.PointDto;
import com.example.Projekt_IO.Services.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/points")
public class PointController {
    private final PointService pointService;
    private final UserInfoService userInfoService;
    @GetMapping("")
    public Set<PointDto> getUsersActivity(){
        String email = userInfoService.getLoggedUserInfo().getEmail();
        return pointService.getUsersActivity(email);
    }

    @GetMapping("/{courseId}")
    public Set<PointDto> getUsersActivityInGroupClass(@PathVariable Long courseId){
        String email = userInfoService.getLoggedUserInfo().getEmail();
        return pointService.getUsersActivityInGroupClass(email,courseId);
    }

    @PostMapping("")//wysylasz mi z formularza obiekt z polami jak ma activity dto
    public void addStudentActivity( @RequestBody PointDto activity){
        String email = userInfoService.getLoggedUserInfo().getEmail();
        pointService.addStudentActivity(email,activity);
    }

}
