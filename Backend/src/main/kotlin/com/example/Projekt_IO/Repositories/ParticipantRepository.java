package com.example.Projekt_IO.Repositories;

import com.example.Projekt_IO.Model.Entities.Course;
import com.example.Projekt_IO.Model.Entities.InvitationStatus;
import com.example.Projekt_IO.Model.Entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ParticipantRepository extends JpaRepository<Participant, UUID> {

    Optional<Participant> findByEmailAndCourse(String email, Course course);

    Set<Participant> findByCourseAndInvitationStatus(Course course, InvitationStatus invitationStatus);

    @Procedure("RejectOldInvites")
    void RejectOldInvites();
}
