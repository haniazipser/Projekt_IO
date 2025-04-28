package com.example.Projekt_IO.Repositories;

import com.example.Projekt_IO.Model.Dtos.ExerciseDto;
import com.example.Projekt_IO.Model.Entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Set<Exercise> findByLesson_Id(Long sessionId);

    Set<Exercise> findByApprovedStudentAndLesson_Id(String email, Long id);
}