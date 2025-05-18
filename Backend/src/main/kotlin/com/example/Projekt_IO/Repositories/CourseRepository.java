package com.example.Projekt_IO.Repositories;

import com.example.Projekt_IO.Model.Entities.Course;
import com.example.Projekt_IO.Model.Entities.InvitationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    Optional<Course> findByCourseCode(String courseCode);

    Set<Course> findDistinctByStudents_EmailAndStudents_InvitationStatus(String email, InvitationStatus invitationStatus);

}