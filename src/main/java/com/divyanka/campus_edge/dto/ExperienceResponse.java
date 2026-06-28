package com.divyanka.campus_edge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceResponse {
    private Long id;
    private String postedByName;
    private String companyName;
    private String roleOffered;
    private Boolean onCampus;
    private Integer interviewYear;
    private String ctcRange;
    private String selectionRatio;
    private Integer totalRounds;
    private String roundsBreakdown;
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
    private Integer upvoteCount;
    private LocalDateTime createdAt;
}