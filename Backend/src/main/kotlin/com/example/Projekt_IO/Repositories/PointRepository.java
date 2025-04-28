package com.example.Projekt_IO.Repositories;

import com.example.Projekt_IO.Model.Entities.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    Set<Point> findByStudent(String email);

    Set<Point> findByStudentAndSource_Id(String email, Long groupId);
}