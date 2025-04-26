package com.example.Projekt_IO.Controllers;

import com.example.Projekt_IO.Model.Dtos.ClassGroupDto;
import com.example.Projekt_IO.Model.Dtos.NewGroupDto;
import com.example.Projekt_IO.Services.GroupClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class ClassGroupController {
    private final GroupClassService groupClassService;
    @PostMapping("/create/{email}")
    public void createGroup(@PathVariable String email, @RequestBody NewGroupDto groupDto){
        groupClassService.createGroup(email, groupDto);
    }

    @GetMapping("/{email}/groups")
    public Set<ClassGroupDto> getStudentGroups(@PathVariable String email){
        return groupClassService.getUsersGroups(email);
    }

}
