package com.example.Projekt_IO.Repositories;

import com.example.Projekt_IO.Model.Dtos.DeclarationDto;
import com.example.Projekt_IO.Model.Entities.Exercise;
import com.example.Projekt_IO.Model.Entities.ExerciseDeclaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Repository
public interface DeclarationRepository extends JpaRepository<ExerciseDeclaration, UUID> {
    Set<ExerciseDeclaration> findByStudent(String email);
    Set<ExerciseDeclaration> findByExercise_Id(UUID exerciseId);

    Set<ExerciseDeclaration> findByStudentAndExercise_Lesson_Id(String email, UUID id);

    Integer countByStudentAndExercise_Lesson_Id(String email, UUID id);

    Set<ExerciseDeclaration> findByStudentAndExercise_Lesson_Course_Id(String email, UUID courseId);
}