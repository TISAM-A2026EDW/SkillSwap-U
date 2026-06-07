package com.epw.skillswap.controller;

import com.epw.skillswap.dto.ExchangeSessionDTO;
import com.epw.skillswap.service.ExchangeSessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class ExchangeSessionController {

    private final ExchangeSessionService sessionService;

    @PostMapping
    public ResponseEntity<ExchangeSessionDTO> createSession(
            @Valid @RequestBody ExchangeSessionDTO dto) {

        return new ResponseEntity<>(
                sessionService.createSession(dto),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExchangeSessionDTO> getSessionById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                sessionService.getSessionById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<ExchangeSessionDTO>> getAllSessions(
            @RequestParam(required = false) UUID teacherUserId,
            @RequestParam(required = false) UUID learnerUserId) {

        if (teacherUserId != null) {
            return ResponseEntity.ok(
                    sessionService.getSessionsByTeacherUserId(teacherUserId)
            );
        }
        if (learnerUserId != null) {
            return ResponseEntity.ok(
                    sessionService.getSessionsByLearnerUserId(learnerUserId)
            );
        }

        return ResponseEntity.ok(
                sessionService.getAllSessions()
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ExchangeSessionDTO> updateStatus(
            @PathVariable UUID id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                sessionService.updateSessionStatus(id, status)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(
            @PathVariable UUID id) {

        sessionService.deleteSession(id);

        return ResponseEntity.noContent().build();
    }
}