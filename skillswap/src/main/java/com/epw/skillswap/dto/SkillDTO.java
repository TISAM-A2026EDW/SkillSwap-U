package com.epw.skillswap.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillDTO {

    private UUID skillId;
    private String skillName;
    private String description;
    private String category;
    private String difficultyLevel;
}