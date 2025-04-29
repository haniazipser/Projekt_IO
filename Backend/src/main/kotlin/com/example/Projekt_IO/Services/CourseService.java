package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.CourseDto;
import com.example.Projekt_IO.Model.Dtos.LessonDto;
import com.example.Projekt_IO.Model.Dtos.NewCourseDto;
import com.example.Projekt_IO.Model.Dtos.UserDto;
import com.example.Projekt_IO.Model.Entities.Course;

import com.example.Projekt_IO.Model.Entities.InvitationStatus;
import com.example.Projekt_IO.Model.Entities.Participant;
import com.example.Projekt_IO.Repositories.CourseRepository;

import com.example.Projekt_IO.Repositories.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final ParticipantRepository participantRepository;
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
        Participant participant = new Participant();
        course = courseRepository.save(course);
        participant.setCourse(course);
        participant.setEmail(email);
        participantRepository.save(participant);
        return new CourseDto(course);
    }

    public void addStudentToGroup(String email, UUID groupId) {
        Optional<Course> classGroup = courseRepository.findById(groupId);
        UserDto loggedUser = userInfoService.getLoggedUserInfo();

        if (classGroup.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
        }else if (!classGroup.get().getCreator().toLowerCase().equals( loggedUser.getEmail().toLowerCase())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"You are not authorized to add students to this group");
        }

        for (Participant sg : classGroup.get().getStudents()){
            if (sg.getEmail().equals(email)){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This student is already in this group");
            }
        }

        Participant participant = new Participant();
        participant.setInvitationStatus(InvitationStatus.WAITING);
        participant.setEmail(email);
        participant.setCourse(classGroup.get());

        participantRepository.save(participant);
    }

    public void deleteStudentFromGroup(String email, UUID groupId) {
        Optional<Course> classGroup = courseRepository.findById(groupId);
        UserDto loggedUser = userInfoService.getLoggedUserInfo();
        if (classGroup.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
        }else if (!classGroup.get().getCreator().toLowerCase().equals(  loggedUser.getEmail().toLowerCase().toLowerCase())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"You are not authorized to delete students to this group");
        }
        boolean found = false;
        for (Participant sg : classGroup.get().getStudents()){
            if (sg.getEmail().equals(email)){
                participantRepository.delete(sg);
                found = true;
                break;
            }
        }
        if (!found){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This student is not in this group");
        }
    }

    public Set<String> getStudentsInGroup(UUID groupId) {
        Optional<Course> course = courseRepository.findById(groupId);
        UserDto loggedUser = userInfoService.getLoggedUserInfo();

        if (course.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
        }


        if (!course.get().isStudenAMemebr(loggedUser.getEmail())){
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
