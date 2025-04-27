package com.example.Projekt_IO.Repositories;

import com.example.Projekt_IO.Model.Entities.ClassGroup;
import com.example.Projekt_IO.Model.Entities.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ClassSessionRepository extends JpaRepository<ClassSession, Long> {
    Optional<ClassSession> findTopByClassGroup_IdAndClassDateAfterOrderByClassDateAsc(Long group, LocalDateTime now);
}