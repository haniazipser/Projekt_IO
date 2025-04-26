package com.example.Projekt_IO.Repositories;

import com.example.Projekt_IO.Model.Entities.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassSessionRepository extends JpaRepository<ClassSession, Long> {}