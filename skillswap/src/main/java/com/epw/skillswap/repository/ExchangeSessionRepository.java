package com.epw.skillswap.repository;

import com.epw.skillswap.entity.ExchangeSession;
import com.epw.skillswap.entity.SessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExchangeSessionRepository
        extends JpaRepository<ExchangeSession, UUID> {

    List<ExchangeSession> findByTeacherUserId(UUID teacherUserId);

    List<ExchangeSession> findByLearnerUserId(UUID learnerUserId);

    List<ExchangeSession> findByStatus(SessionStatus status);
}