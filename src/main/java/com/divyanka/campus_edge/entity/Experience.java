package com.divyanka.campus_edge.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "experiences")
@Data
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posted_by", nullable = false)
    private User postedBy;

    @Column(name = "company_name", nullable = false, length = 150)
    private String companyName;

    @Column(name = "role_offered", nullable = false, length = 150)
    private String roleOffered;

    @Column(name = "on_campus", nullable = false)
    private Boolean onCampus = true;

    @Column(name = "interview_year", nullable = false)
    private Integer interviewYear;

    @Column(name = "ctc_range", length = 50)
    private String ctcRange;

    @Column(name = "selection_ratio", length = 100)
    private String selectionRatio;

    @Column(name = "total_rounds", nullable = false)
    private Integer totalRounds;

    @Column(name = "rounds_breakdown", columnDefinition = "TEXT", nullable = false)
    private String roundsBreakdown;

    @Column(name = "questions_asked", columnDefinition = "TEXT", nullable = false)
    private String questionsAsked;

    @Column(name = "topics_tested", length = 500)
    private String topicsTested;

    @Column(name = "difficulty_rating")
    private Integer difficultyRating;

    @Column(name = "prep_duration", length = 50)
    private String prepDuration;

    @Column(name = "prep_resources", columnDefinition = "TEXT")
    private String prepResources;

    @Column(name = "cgpa_at_placement", length = 20)
    private String cgpaAtPlacement;

    @Column(name = "backlog_status", length = 50)
    private String backlogStatus;

    @Column(name = "advice_focus", columnDefinition = "TEXT")
    private String adviceFocus;

    @Column(name = "mistakes_to_avoid", columnDefinition = "TEXT")
    private String mistakesToAvoid;

    @Column(name = "hr_tips", columnDefinition = "TEXT")
    private String hrTips;

    @Column(name = "overall_experience", length = 50)
    private String overallExperience;

    @Column(name = "is_anonymous", nullable = false)
    private Boolean isAnonymous = false;

    @Column(name = "upvote_count", nullable = false)
    private Integer upvoteCount = 0;

    @Column(name = "is_approved", nullable = false)
    private Boolean isApproved = true;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}