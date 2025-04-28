package com.example.Projekt_IO.Repositories;

import com.example.Projekt_IO.Model.Entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, UUID> {
    Optional<Lesson> findTopByCourse_IdAndClassDateAfterOrderByClassDateAsc(UUID group, LocalDateTime now);
}