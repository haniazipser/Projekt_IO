package com.example.Projekt_IO.Controllers;

import com.example.Projekt_IO.Model.Dtos.CourseDto;
import com.example.Projekt_IO.Model.Dtos.LessonDto;
import com.example.Projekt_IO.Model.Dtos.NewCourseDto;
import com.example.Projekt_IO.Services.CourseApplicationService;
import com.example.Projekt_IO.Services.CourseService;
import com.example.Projekt_IO.Services.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseApplicationService courseApplicationService;
    private final UserInfoService userInfoService;
    @PostMapping("/create")
    public CourseDto createGroup(@RequestBody NewCourseDto newCourseDto){
        String email = userInfoService.getLoggedUserInfo().getEmail();
        return courseService.createCourse(email, newCourseDto);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDto>> getStudentGroups(){
        String email = userInfoService.getLoggedUserInfo().getEmail();
        System.out.println(email);
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(30, TimeUnit.DAYS).mustRevalidate())
                .body(courseService.getUsersGroups(email));
    }

    @GetMapping("{courseId}/students")
    public ResponseEntity<List<String>> getStudentsInGroup(@PathVariable UUID courseId){
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(30, TimeUnit.DAYS).mustRevalidate())
                .body(courseService.getStudentsInGroup(courseId));
    }

    @PostMapping("/{email}/{courseId}")
    public void addStudentToCourse(@PathVariable String email, @PathVariable UUID courseId){
        courseApplicationService.addStudentToGroup(email, courseId);
    }

    @PostMapping("/{groupCode}")
    public void joinCourse(@PathVariable String groupCode){
        courseService.joinCourse(groupCode);
    }

    @DeleteMapping("/{email}/{courseId}")
    public void deleteStudentFromGroup(@PathVariable String email, @PathVariable UUID courseId){
        courseService.deleteStudentFromGroup(email,courseId);
    }

    @DeleteMapping("/{courseId}")
    public void archiveCourse(@PathVariable UUID courseId){
        courseService.archiveCourse(courseId);
    }

}
