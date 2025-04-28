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
@RequestMapping("/group")
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

    @GetMapping("/groups")
    public Set<CourseDto> getStudentGroups(){
        String email = userInfoService.getLoggedUserInfo().getEmail();
        System.out.println(email);
        return groupClassService.getUsersGroups(email);
    }

    @GetMapping("{groupId}/students")
    public Set<String> getStudentsInGroup(@PathVariable Long groupId){
        return groupClassService.getStudentsInGroup(groupId);
    }

    @PostMapping("/{email}/{groupId}")//  EWENTUALNIE MOGE CI DODAC LISTE USEROW
    public void addStudentToGroup(@PathVariable String email, @PathVariable Long groupId){
        courseApplicationService.addStudentToGroup(email, groupId);
    }

    @DeleteMapping("/{email}/{groupId}")
    public void deleteStudentFromGroup(@PathVariable String email, @PathVariable Long groupId){
        groupClassService.deleteStudentFromGroup(email,groupId);
    }

    @PostMapping("/accept")
    public void acceptInvitation(@RequestParam Long groupId) {
        String email = userInfoService.getLoggedUserInfo().getEmail();
        groupClassService.acceptInvite(email, groupId);
    }
}
