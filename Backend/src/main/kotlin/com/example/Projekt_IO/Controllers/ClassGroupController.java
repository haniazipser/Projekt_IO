package com.example.Projekt_IO.Controllers;

import com.example.Projekt_IO.Model.Dtos.ClassGroupDto;
import com.example.Projekt_IO.Model.Dtos.NewGroupDto;
import com.example.Projekt_IO.Services.GroupClassApplicationService;
import com.example.Projekt_IO.Services.GroupClassService;
import com.example.Projekt_IO.Services.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class ClassGroupController {
    //komentarz

    private final GroupClassService groupClassService;
    private final GroupClassApplicationService groupClassApplicationService;
    private final UserInfoService userInfoService;
    @PostMapping("/create")
    public void createGroup(@RequestBody NewGroupDto groupDto){
        String email = userInfoService.getLoggedUserInfo().getEmail();
        groupClassService.createGroup(email, groupDto);
    }

    @GetMapping("/groups")
    public Set<ClassGroupDto> getStudentGroups(){
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
        groupClassApplicationService.addStudentToGroup(email, groupId);
    }

    @DeleteMapping("/{email}/{groupId}")
    public void deleteStudentFromGroup(@PathVariable String email, @PathVariable Long groupId){
        groupClassService.deleteStudentFromGroup(email,groupId);
    }

}
