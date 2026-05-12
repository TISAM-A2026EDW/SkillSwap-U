package com.epw.skillswap.service;

import com.epw.skillswap.dto.ExchangeSessionDTO;

import java.util.List;
import java.util.UUID;

public interface ExchangeSessionService {

    ExchangeSessionDTO createSession(ExchangeSessionDTO dto);

    ExchangeSessionDTO getSessionById(UUID sessionId);

    List<ExchangeSessionDTO> getAllSessions();

    ExchangeSessionDTO updateSessionStatus(UUID sessionId, String status);

    void deleteSession(UUID sessionId);
}