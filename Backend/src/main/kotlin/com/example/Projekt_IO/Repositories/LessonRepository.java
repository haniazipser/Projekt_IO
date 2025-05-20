package com.example.Projekt_IO.Repositories;

import com.example.Projekt_IO.Model.Dtos.LessonDto;
import com.example.Projekt_IO.Model.Entities.Course;
import com.example.Projekt_IO.Model.Entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, UUID> {
    Optional<Lesson> findTopByCourse_IdAndClassDateAfterOrderByClassDateAsc(UUID group, Instant now);

    List<Lesson> findByCourse(Course course);

    List<Lesson> findByClassDate(LocalDate tomorrow);

    @Query("SELECT L.id FROM Lesson L WHERE L.classDate >= :start AND L.classDate < :end")
    List<UUID> findLessonIdsByClassDateBetween(@Param("start") Instant start, @Param("end") Instant end);
}