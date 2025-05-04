package com.example.Projekt_IO.Controllers;

import com.example.Projekt_IO.Model.Dtos.CourseDto;
import com.example.Projekt_IO.Model.Dtos.LessonDto;
import com.example.Projekt_IO.Model.Dtos.NewCourseDto;
import com.example.Projekt_IO.Services.CourseApplicationService;
import com.example.Projekt_IO.Services.CourseService;
import com.example.Projekt_IO.Services.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    //komentarz

    private final CourseService courseService;
    private final CourseApplicationService courseApplicationService;
    private final UserInfoService userInfoService;
    @PostMapping("/create")
    public CourseDto createGroup(@RequestBody NewCourseDto newCourseDto){
        String email = userInfoService.getLoggedUserInfo().getEmail();
        return courseService.createCourse(email, newCourseDto);
    }

    @GetMapping("/courses")
    public Set<CourseDto> getStudentGroups(){
        String email = userInfoService.getLoggedUserInfo().getEmail();
        System.out.println(email);
        return courseService.getUsersGroups(email);
    }

    @GetMapping("{courseId}/students")
    public Set<String> getStudentsInGroup(@PathVariable UUID courseId){
        return courseService.getStudentsInGroup(courseId);
    }

    @PostMapping("/{email}/{courseId}")//  EWENTUALNIE MOGE CI DODAC LISTE USEROW
    public void addStudentToGroup(@PathVariable String email, @PathVariable UUID courseId){
        courseApplicationService.addStudentToGroup(email, courseId);
    }

    @DeleteMapping("/{email}/{courseId}")
    public void deleteStudentFromGroup(@PathVariable String email, @PathVariable UUID courseId){
        courseService.deleteStudentFromGroup(email,courseId);
    }

    @PostMapping("/accept")
    public void acceptInvitation(@RequestParam UUID courseId) {
        String email = userInfoService.getLoggedUserInfo().getEmail();
        courseService.acceptInvite(email, courseId);
    }

}
