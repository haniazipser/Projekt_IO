package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Repositories.ParticipantRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DatabaseCleanupService {
    private final ParticipantRepository participantRepository;
    Logger logger = LoggerFactory.getLogger(DatabaseCleanupService.class);

    @Scheduled(cron = "0 0 3 * * ?")
    @Transactional
    public void cleanupOldParticipants() {
        logger.info("Starting daily participant cleanup");

        try {
            participantRepository.RejectOldInvites();
            logger.info("Participant cleanup completed successfully");

        } catch (Exception e) {
            logger.error("Error during participant cleanup", e);
        }
    }
}
