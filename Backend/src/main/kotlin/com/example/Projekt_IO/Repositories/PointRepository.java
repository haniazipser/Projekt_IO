package com.example.Projekt_IO.Repositories;

import com.example.Projekt_IO.Model.Entities.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Repository
public interface PointRepository extends JpaRepository<Point, UUID> {
    Set<Point> findByStudent(String email);

    Set<Point> findByStudentAndLesson_Course_Id(String email, UUID courseId);
}