package com.divyanka.campus_edge.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateExperienceRequest {
    @NotBlank
    private String companyName;

    @NotBlank
    private String roleOffered;

    @NotNull
    private Boolean onCampus;

    @NotNull
    private Integer interviewYear;

    private String ctcRange;
    private String selectionRatio;

    @NotNull
    private Integer totalRounds;

    @NotBlank
    private String roundsBreakdown;

    @NotBlank
    private String questionsAsked;

    private String topicsTested;
    private Integer difficultyRating;
    private String prepDuration;
    private String prepResources;
    private String cgpaAtPlacement;
    private String backlogStatus;
    private String adviceFocus;
    private String mistakesToAvoid;
    private String hrTips;
    private String overallExperience;

    private Boolean isAnonymous = false;
}