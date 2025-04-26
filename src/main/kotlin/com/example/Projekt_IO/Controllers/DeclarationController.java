package com.example.Projekt_IO.Controllers;

import com.example.Projekt_IO.Model.Dtos.DeclarationDto;
import com.example.Projekt_IO.Services.DeclarationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/declaration")
public class DeclarationController {
    private final DeclarationService declarationService;
    @PostMapping("/{email}/{exerciseId}")
    public void declareExercise (@PathVariable String email, @PathVariable Long exerciseId){
        declarationService.declareExercise(email, exerciseId);
    }

    @GetMapping("/{email}")
    public Set<DeclarationDto> getUsersDeclarations (@PathVariable String email){
        return declarationService.getUsersDeclarations(email);
    }

    @PostMapping("/runMatching")
    public void runMatchingAlgorithm(){
        declarationService.runMatchingAlgorithm();
    }
}
