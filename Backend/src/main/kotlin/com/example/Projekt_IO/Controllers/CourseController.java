package com.example.Projekt_IO.Controllers;

import com.example.Projekt_IO.Model.Dtos.CourseDto;
import com.example.Projekt_IO.Model.Dtos.NewCourseDto;
import com.example.Projekt_IO.Services.CourseApplicationService;
import com.example.Projekt_IO.Services.CourseService;
import com.example.Projekt_IO.Services.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    //komentarz

    private final CourseService groupClassService;
    private final CourseApplicationService courseApplicationService;
    private final UserInfoService userInfoService;
    @PostMapping("/create")
    public void createGroup(@RequestBody NewCourseDto groupDto){
        String email = userInfoService.getLoggedUserInfo().getEmail();
        groupClassService.createGroup(email, groupDto);
    }

    @GetMapping("/courses")
    public Set<CourseDto> getStudentGroups(){
        String email = userInfoService.getLoggedUserInfo().getEmail();
        System.out.println(email);
        return groupClassService.getUsersGroups(email);
    }

    @GetMapping("{courseId}/students")
    public Set<String> getStudentsInGroup(@PathVariable Long courseId){
        return groupClassService.getStudentsInGroup(courseId);
    }

    @PostMapping("/{email}/{courseId}")//  EWENTUALNIE MOGE CI DODAC LISTE USEROW
    public void addStudentToGroup(@PathVariable String email, @PathVariable Long courseId){
        courseApplicationService.addStudentToGroup(email, courseId);
    }

    @DeleteMapping("/{email}/{courseId}")
    public void deleteStudentFromGroup(@PathVariable String email, @PathVariable Long courseId){
        groupClassService.deleteStudentFromGroup(email,courseId);
    }

    @PostMapping("/accept")
    public void acceptInvitation(@RequestParam Long courseId) {
        String email = userInfoService.getLoggedUserInfo().getEmail();
        groupClassService.acceptInvite(email, courseId);
    }
}
