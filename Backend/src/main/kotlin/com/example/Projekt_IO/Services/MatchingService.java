package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.DeclarationShortDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MatchingService {
    private final DeclarationService declarationService;


    public void matchingAlgorithm(UUID lessonId){
        List<DeclarationShortDto> declarations = declarationService.getAllDeclarationsForLesson(lessonId);

    }
}
