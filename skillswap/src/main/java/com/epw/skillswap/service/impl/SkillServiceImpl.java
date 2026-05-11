package com.epw.skillswap.service.impl;


import com.epw.skillswap.dto.SkillDTO;
import com.epw.skillswap.entity.Skill;
import com.epw.skillswap.exception.ResourceNotFoundException;
import com.epw.skillswap.repository.SkillRepository;
import com.epw.skillswap.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SkillServiceImpl implements SkillService {

    private final SkillRepository repository;

    @Override
    public SkillDTO create(SkillDTO dto) {

        Skill skill = Skill.builder()
                .skillName(dto.getSkillName())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .difficultyLevel(dto.getDifficultyLevel())
                .build();

        return mapToDTO(repository.save(skill));
    }

    @Override
    public List<SkillDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public SkillDTO getById(UUID id) {

        Skill skill = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));

        return mapToDTO(skill);
    }

    @Override
    public SkillDTO update(UUID id, SkillDTO dto) {

        Skill skill = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));

        skill.setSkillName(dto.getSkillName());
        skill.setDescription(dto.getDescription());

        return mapToDTO(repository.save(skill));
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private SkillDTO mapToDTO(Skill skill){
        return SkillDTO.builder()
                .skillId(skill.getSkillId())
                .skillName(skill.getSkillName())
                .description(skill.getDescription())
                .category(skill.getCategory())
                .difficultyLevel(skill.getDifficultyLevel())
                .build();
    }
}