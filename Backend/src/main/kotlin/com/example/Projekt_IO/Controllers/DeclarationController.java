package com.example.Projekt_IO.Controllers;

import com.example.Projekt_IO.Model.Dtos.DeclarationDto;
import com.example.Projekt_IO.Services.DeclarationService;
import com.example.Projekt_IO.Services.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/declaration")
public class DeclarationController {
    private final DeclarationService declarationService;
    private final UserInfoService userInfoService;
    @PostMapping("/{exerciseId}")
    public void declareExercise ( @PathVariable Long exerciseId){
        String email = userInfoService.getLoggedUserInfo().getEmail();
        declarationService.declareExercise(email, exerciseId);
    }

    @GetMapping("")
    public Set<DeclarationDto> getStudentDeclarations (){
        String email = userInfoService.getLoggedUserInfo().getEmail();
        return declarationService.getUsersDeclarations(email);
    }

    @PostMapping("/runMatching")
    public void runMatchingAlgorithm(){
        declarationService.runMatchingAlgorithm();
    }
}
