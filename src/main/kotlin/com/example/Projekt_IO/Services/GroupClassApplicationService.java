package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.ClassGroupDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupClassApplicationService {
    private final GroupClassService groupClassService;
    private final EmailService emailService;
    private final UserInfoService userInfoService;

    public void addStudentToGroup(String email, Long groupId) {
        groupClassService.addStudentToGroup(email, groupId);
        ClassGroupDto group = groupClassService.getGroupInfo(groupId);
        StringBuilder builder = new StringBuilder();
        builder.append("You have been added to ").append(group.getName()).append(" group");
        emailService.sendMessage(email,"New group Alert", builder.toString());
    }
}
