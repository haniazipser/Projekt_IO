package com.example.Projekt_IO.Repositories;

import com.example.Projekt_IO.Model.Dtos.ActivityDto;
import com.example.Projekt_IO.Model.Entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Set<Activity> findByStudent(String email);

    Set<Activity> findByStudentAndSource_Id(String email, Long groupId);
}