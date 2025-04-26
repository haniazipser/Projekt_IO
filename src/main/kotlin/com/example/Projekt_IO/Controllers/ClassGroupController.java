package com.example.Projekt_IO.Controllers;

import com.example.Projekt_IO.Model.Dtos.ClassGroupDto;
import com.example.Projekt_IO.Model.Dtos.NewGroupDto;
import com.example.Projekt_IO.Services.GroupClassApplicationService;
import com.example.Projekt_IO.Services.GroupClassService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class ClassGroupController {

    private final GroupClassService groupClassService;
    private final GroupClassApplicationService groupClassApplicationService;
    @PostMapping("/create/{email}")
    public void createGroup(@PathVariable String email, @RequestBody NewGroupDto groupDto){
        groupClassService.createGroup(email, groupDto);
    }

    @GetMapping("/{email}/groups")
    public Set<ClassGroupDto> getStudentGroups(@PathVariable String email){
        return groupClassService.getUsersGroups(email);
    }

    @GetMapping("{groupId}/students")
    public Set<String> getStudentsInGroup(@PathVariable Long groupId){
        return groupClassService.getStudentsInGroup(groupId);
    }

    @PutMapping("/{email}/{groupId}")//  EWENTUALNIE MOGE CI DODAC LISTE USEROW
    public void addStudentToGroup(@PathVariable String email, @PathVariable Long groupId){
        groupClassApplicationService.addStudentToGroup(email, groupId);
    }

    @DeleteMapping("/{email}/{groupId}")
    public void deleteStudentFromGroup(@PathVariable String email, @PathVariable Long groupId){
        groupClassService.deleteStudentFromGroup(email,groupId);
    }

}
