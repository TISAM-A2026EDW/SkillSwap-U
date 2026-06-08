package com.epw.skillswap.controller;

import com.epw.skillswap.dto.ExploreItemDTO;
import com.epw.skillswap.service.ExploreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/explore")
@RequiredArgsConstructor
public class ExploreController {

    private final ExploreService exploreService;

    @GetMapping
    public List<ExploreItemDTO> getExplore(
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) String level,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "rating") String sort) {
        return exploreService.getExplore(categoryId, level, search, sort);
    }
}
