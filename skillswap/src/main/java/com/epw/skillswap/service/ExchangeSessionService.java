package com.epw.skillswap.service;

import com.epw.skillswap.dto.BookSessionRequest;
import com.epw.skillswap.dto.ExchangeSessionDTO;

import java.util.List;
import java.util.UUID;

public interface ExchangeSessionService {

    ExchangeSessionDTO createSession(ExchangeSessionDTO dto);

    ExchangeSessionDTO bookSession(UUID learnerId, BookSessionRequest request);

    ExchangeSessionDTO getSessionById(UUID sessionId);

    List<ExchangeSessionDTO> getAllSessions();

    List<ExchangeSessionDTO> getSessionsByTeacherUserId(UUID teacherUserId);

    List<ExchangeSessionDTO> getSessionsByLearnerUserId(UUID learnerUserId);

    ExchangeSessionDTO updateSessionStatus(UUID sessionId, String status);

    void deleteSession(UUID sessionId);
}