package com.example.Projekt_IO.Repositories;

import com.example.Projekt_IO.Model.Entities.Course;
import com.example.Projekt_IO.Model.Entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ParticipantRepository extends JpaRepository<Participant, UUID> {

    Optional<Participant> findByEmailAndCourse(String email, Course course);
}
