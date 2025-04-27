package com.example.Projekt_IO.Repositories;

import com.example.Projekt_IO.Model.Entities.ClassGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ClassGroupRepository extends JpaRepository<ClassGroup, Long> {
    Set<ClassGroup> findDistinctByStudents_Email(String email);
}