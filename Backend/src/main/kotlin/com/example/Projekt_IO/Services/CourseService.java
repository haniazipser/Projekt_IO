package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.CourseDto;
import com.example.Projekt_IO.Model.Dtos.NewCourseDto;
import com.example.Projekt_IO.Model.Dtos.UserDto;
import com.example.Projekt_IO.Model.Entities.*;

import com.example.Projekt_IO.Repositories.CourseRepository;

import com.example.Projekt_IO.Repositories.LessonRepository;
import com.example.Projekt_IO.Repositories.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final ParticipantRepository participantRepository;
    private final LessonRepository lessonRepository;
    private final UserInfoService userInfoService;
    Logger logger = LoggerFactory.getLogger(CourseService.class);
    public Set<CourseDto> getUsersGroups (String email){
      return courseRepository.findDistinctByStudents_Email(email).stream().map(g -> new CourseDto(g)).collect(Collectors.toSet());
    }

    public CourseDto createCourse(String email, NewCourseDto courseDto) {
        Course course = new Course();
        course.setCreator(email);
        course.setLessonTimes(courseDto.getLessonTimes());
        course.setName(courseDto.getName());
        course.setInstructor(courseDto.getInstructor());
        course.setStartDate(courseDto.getStartDate());
        course.setEndDate(courseDto.getEndDate());
        course.setFrequency(courseDto.getFrequency());

        course = courseRepository.save(course);

        Set<Lesson> lessons = new HashSet<>();

        Instant endDate = courseDto.getEndDate();
        for (LessonTime lessonTime : course.getLessonTimes()) {
            Instant next = course.getFirstLesson(lessonTime);
            System.out.println("Dodaję lekcję: " + next);
            while (next.isBefore(endDate)){
                Lesson lesson = new Lesson();
                lesson.setClassDate(next);
                lesson.setCourse(course);
                lessonRepository.save(lesson);
                lessons.add(lesson);
                next  = next.plus(7 * course.getFrequency(), ChronoUnit.DAYS);
            }
        }
        course.setLessons(lessons);

        Participant participant = new Participant();
        participant.setCourse(course);
        participant.setEmail(email);
        participantRepository.save(participant);
        return new CourseDto(course);
    }

    public void addStudentToGroup(String email, UUID groupId) {
        Optional<Course> course = courseRepository.findById(groupId);
        UserDto loggedUser = userInfoService.getLoggedUserInfo();

        if (course.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
        }else if (!course.get().getCreator().toLowerCase().equals( loggedUser.getEmail().toLowerCase())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"You are not authorized to add students to this group");
        }else if (course.get().isStudentAMemeber(email)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This student is already a member");
        }

        Participant participant = new Participant();
        participant.setInvitationStatus(InvitationStatus.WAITING);
        participant.setEmail(email);
        participant.setCourse(course.get());

        participantRepository.save(participant);
    }

    public void deleteStudentFromGroup(String email, UUID groupId) {
        Optional<Course> course = courseRepository.findById(groupId);
        UserDto loggedUser = userInfoService.getLoggedUserInfo();
        if (course.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
        }else if (!course.get().getCreator().toLowerCase().equals(  loggedUser.getEmail().toLowerCase().toLowerCase())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"You are not authorized to delete students to this group");
        }else if (!course.get().isStudentAMemeber(email)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This student is not a member");
        }

        Participant participant = participantRepository.findByEmailAndCourse_Id(email,groupId);
        participantRepository.delete(participant);
    }

    public Set<String> getStudentsInGroup(UUID groupId) {
        Optional<Course> course = courseRepository.findById(groupId);
        UserDto loggedUser = userInfoService.getLoggedUserInfo();

        if (course.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
        }else if (!course.get().isStudentAMemeber(loggedUser.getEmail())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"You are not a member of this group");
        }

        return course.get().getStudents().stream().map(s -> s.getEmail()).collect(Collectors.toSet());
    }

    public CourseDto getGroupInfo(UUID groupId){
        Course course = courseRepository.findById(groupId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found"));
        return new CourseDto(course);
    }

    public void acceptInvite(String email, UUID groupId) {
        Participant participant = participantRepository.findByEmailAndCourse_Id(email,groupId);
        participant.setInvitationStatus(InvitationStatus.ACCEPTED);
        participantRepository.save(participant);
    }


}
