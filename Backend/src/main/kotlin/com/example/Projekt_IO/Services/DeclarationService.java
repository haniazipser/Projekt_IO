package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.DeclarationStatus;
import com.example.Projekt_IO.Model.Dtos.DeclarationDto;
import com.example.Projekt_IO.Model.Entities.Exercise;
import com.example.Projekt_IO.Model.Entities.ExerciseDeclaration;
import com.example.Projekt_IO.Repositories.DeclarationRepository;
import com.example.Projekt_IO.Repositories.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeclarationService {
    private  final DeclarationRepository declarationRepository;
    private final ExerciseRepository exerciseRepository;
    public void declareExercise(String email, Long exerciseId) {
        ExerciseDeclaration declaration = new ExerciseDeclaration();
        Optional<Exercise> exercise = exerciseRepository.findById(exerciseId);
        if (exercise.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found");
        }else if(exercise.get().getLesson().getClassDate().isBefore(LocalDateTime.now())){
            throw new IllegalStateException("You can not declare exercises from past class lessons");
        }//MOZEMY DODAC ZE DEKLAROWAC MOZNA TYLKO 24 GODZ PRZED
        ///SPRAWDZIC CZY STUDENT NALEZY DO GRUPY ZAJECIOWEJ Z KTOREJ JEST TO ZADANIE

        declaration.setExercise(exercise.get());
        declaration.setDeclarationDate(LocalDateTime.now());
        declaration.setStudent(email);
        declaration.setDeclarationStatus(DeclarationStatus.WAITING);
        declarationRepository.save(declaration);
    }

    public Set<DeclarationDto> getUsersDeclarations(String email) {
        return declarationRepository.findByStudent(email).stream().map(d -> new DeclarationDto(d)).collect(Collectors.toSet());
    }

    public void runMatchingAlgorithm(){
        //nasz algorytm jak dopasuje exercise, student musi zrobic:
       /* Exercise exercise = exerciseRepository.findById(exerciseId);
        exercise.setApprovedStudent(email);
        Set<ExerciseDeclaration> rejectedDeclarations = declarationRepository.findByExercise_Id(exerciseId);
        for (ExerciseDeclaration r : rejectedDeclarations){
            r.setDeclarationStatus(DeclarationStatus.REJECTED);
            declarationRepository.save(r);
        }*/

    }

    public Set<DeclarationDto> getUsersDeclarationsForSession(String email, Long id) {
        return declarationRepository.findByStudentAndExerciseLesson_Id(email,id).stream().map(d -> new DeclarationDto(d)).collect(Collectors.toSet());
    }

    public Integer getDeclarationsForSessionCount(String email, Long id) {
        return declarationRepository.countByStudentAndExerciseLesson_Id(email,id);
    }
}
