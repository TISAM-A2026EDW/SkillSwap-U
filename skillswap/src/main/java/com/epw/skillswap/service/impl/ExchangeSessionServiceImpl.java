package com.epw.skillswap.service.impl;

import com.epw.skillswap.dto.ExchangeSessionDTO;
import com.epw.skillswap.entity.*;
import com.epw.skillswap.exception.ResourceNotFoundException;
import com.epw.skillswap.repository.ExchangeSessionRepository;
import com.epw.skillswap.repository.SkillRepository;
import com.epw.skillswap.repository.UserRepository;
import com.epw.skillswap.service.ExchangeSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ExchangeSessionServiceImpl
        implements ExchangeSessionService {

    private final ExchangeSessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;

    @Override
    public ExchangeSessionDTO createSession(
            ExchangeSessionDTO dto) {

        User teacher = userRepository.findById(dto.getTeacherUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Teacher not found"));

        User learner = userRepository.findById(dto.getLearnerUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Learner not found"));

        Skill skill = skillRepository.findById(dto.getSkillId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Skill not found"));

        ExchangeSession session = ExchangeSession.builder()
                .teacher(teacher)
                .learner(learner)
                .skill(skill)
                .scheduledDate(dto.getScheduledDate())
                .durationHours(dto.getDurationHours())
                .creditsExchanged(dto.getCreditsExchanged())
                .meetingLink(dto.getMeetingLink())
                .sessionNotes(dto.getSessionNotes())
                .status(SessionStatus.PENDING)
                .build();

        return mapToDTO(sessionRepository.save(session));
    }

    @Override
    @Transactional(readOnly = true)
    public ExchangeSessionDTO getSessionById(UUID sessionId) {

        ExchangeSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Session not found"));

        return mapToDTO(session);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExchangeSessionDTO> getAllSessions() {

        return sessionRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExchangeSessionDTO updateSessionStatus(
            UUID sessionId,
            String status) {

        ExchangeSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Session not found"));

        session.setStatus(SessionStatus.valueOf(status.toUpperCase()));

        return mapToDTO(sessionRepository.save(session));
    }

    @Override
    public void deleteSession(UUID sessionId) {

        ExchangeSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Session not found"));

        sessionRepository.delete(session);
    }

    private ExchangeSessionDTO mapToDTO(
            ExchangeSession session) {

        return ExchangeSessionDTO.builder()
                .sessionId(session.getSessionId())
                .teacherUserId(session.getTeacher().getUserId())
                .learnerUserId(session.getLearner().getUserId())
                .skillId(session.getSkill().getSkillId())
                .scheduledDate(session.getScheduledDate())
                .durationHours(session.getDurationHours())
                .creditsExchanged(session.getCreditsExchanged())
                .meetingLink(session.getMeetingLink())
                .sessionNotes(session.getSessionNotes())
                .status(session.getStatus().name())
                .build();
    }
}