package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.DeclarationShortDto;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.HashMap;



@Service
@RequiredArgsConstructor
public class MatchingService {
    private final DeclarationService declarationService;
    private final ExerciseRepository exerciseRepository;

    public void matchingAlgorithm(UUID lessonId){
        List<DeclarationShortDto> declarations = declarationService.getAllDeclarationsForLesson(lessonId);

    }
}
