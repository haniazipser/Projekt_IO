package com.example.Projekt_IO.Controllers;

import com.example.Projekt_IO.Services.MatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TestingMatching {
    private final MatchingService matchingService;
    @PostMapping("/match/{lessonId}")
    public void runMatchingAlgorithm(@PathVariable UUID lessonId){
        matchingService.matchingAlgorithm( lessonId);
    }
}
