package com.example.Projekt_IO.Repositories;

import com.example.Projekt_IO.Model.Entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParticipantRepository extends JpaRepository<Participant, UUID> {

    Participant findByEmailAndCourse_Id(String email, UUID groupId);
}
