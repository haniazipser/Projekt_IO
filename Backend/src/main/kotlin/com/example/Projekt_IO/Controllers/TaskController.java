package com.example.Projekt_IO.Controllers;

import com.example.Projekt_IO.Model.Dtos.TaskDto;
import com.example.Projekt_IO.Services.ClassSessionService;
import com.example.Projekt_IO.Services.GroupClassApplicationService;
import com.example.Projekt_IO.Services.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final UserInfoService userInfoService;
    private final GroupClassApplicationService groupClassApplicationService;

    @GetMapping("")
    public Set<TaskDto> getStudentsTasks(){
        System.out.println("w kontrolerze");
        String email = userInfoService.getLoggedUserInfo().getEmail();
        return groupClassApplicationService.getStudentsTasks(email);
    }
}
