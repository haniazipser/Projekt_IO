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

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeclarationService {
    private  final DeclarationRepository declarationRepository;
    private final ExerciseRepository exerciseRepository;
    public void declareExercise(String email, UUID exerciseId) {
        ExerciseDeclaration declaration = new ExerciseDeclaration();
        Optional<Exercise> exercise = exerciseRepository.findById(exerciseId);
        if (exercise.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found");
        }else if(exercise.get().getLesson().getClassDate().isBefore(Instant.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can not declare exercises frm past lessons");
        }else if (!exercise.get().getLesson().getCourse().isStudentAMemeber(email)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Tou are not a member of this course");
        }else if (exercise.get().getLesson().getHoursToLesson() < 12){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can no longer declare exercises");
        }

        declaration.setExercise(exercise.get());
        declaration.setDeclarationDate(Instant.now());
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

    public Set<DeclarationDto> getDeclarationsForLesson(String email, UUID id) {
        return declarationRepository.findByStudentAndExercise_Lesson_Id(email,id).stream().map(d -> new DeclarationDto(d)).collect(Collectors.toSet());
    }

    public Integer getDeclarationsForSessionCount(String email, UUID id) {
        return declarationRepository.countByStudentAndExercise_Lesson_Id(email,id);
    }

    public Set<DeclarationDto> getUsersDeclarationsInCourse(String email, UUID courseId) {
        return declarationRepository.findByStudentAndExercise_Lesson_Course_Id(email,courseId).stream().map(d -> new DeclarationDto(d)).collect(Collectors.toSet());
    }

    public void deleteDeclaration(UUID declarationId) {
        Optional<ExerciseDeclaration> declaration = declarationRepository.findById(declarationId);
        if (declaration.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Declaration not found");
        } else if (declaration.get().getDeclarationStatus() == DeclarationStatus.APPROVED){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can not modify this declaration");
        }
        declarationRepository.delete(declaration.get());
    }

}
