package com.example.Projekt_IO.Repositories;

import com.example.Projekt_IO.Model.Entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    Set<Course> findDistinctByStudents_Email(String email);

    Optional<Course> findByCourseCode(String courseCode);
}