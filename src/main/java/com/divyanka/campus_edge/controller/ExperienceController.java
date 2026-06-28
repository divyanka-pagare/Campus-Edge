package com.divyanka.campus_edge.controller;

import com.divyanka.campus_edge.dto.CreateExperienceRequest;
import com.divyanka.campus_edge.dto.ExperienceResponse;
import com.divyanka.campus_edge.service.ExperienceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @PostMapping
    public ResponseEntity<ExperienceResponse> create(
            @Valid @RequestBody CreateExperienceRequest req,
            Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(experienceService.createExperience(email, req));
    }

    @GetMapping
    public ResponseEntity<List<ExperienceResponse>> getAll(
            @RequestParam(required = false) String company,
            @RequestParam(required = false) Integer year) {
        return ResponseEntity.ok(experienceService.getExperiences(company, year));
    }

    @PostMapping("/{id}/upvote")
    public ResponseEntity<Void> upvote(@PathVariable Long id, Authentication authentication) {
        experienceService.upvoteExperience(id, authentication.getName());
        return ResponseEntity.ok().build();
    }
}