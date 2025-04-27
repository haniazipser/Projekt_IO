package com.example.Projekt_IO.Repositories;

import com.example.Projekt_IO.Model.Dtos.DeclarationDto;
import com.example.Projekt_IO.Model.Entities.Exercise;
import com.example.Projekt_IO.Model.Entities.ExerciseDeclaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DeclarationRepository extends JpaRepository<ExerciseDeclaration, Long> {
    Set<ExerciseDeclaration> findByStudent(String email);
    Set<ExerciseDeclaration> findByExercise_Id(Long exerciseId);

    Set<ExerciseDeclaration> findByStudentAndExerciseClassSession_Id(String email, Long id);

    Integer countByStudentAndExerciseClassSession_Id(String email, Long id);
}