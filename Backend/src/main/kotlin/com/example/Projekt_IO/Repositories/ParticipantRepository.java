package com.example.Projekt_IO.Repositories;

import com.example.Projekt_IO.Model.Entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    Participant findByEmailAndCourse_Id(String email, Long groupId);
}
