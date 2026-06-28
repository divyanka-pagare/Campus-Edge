package com.divyanka.campus_edge.service;

import com.divyanka.campus_edge.dto.CreateExperienceRequest;
import com.divyanka.campus_edge.dto.ExperienceResponse;
import com.divyanka.campus_edge.entity.Experience;
import com.divyanka.campus_edge.entity.ExperienceUpvote;
import com.divyanka.campus_edge.entity.User;
import com.divyanka.campus_edge.exception.BadRequestException;
import com.divyanka.campus_edge.repository.ExperienceRepository;
import com.divyanka.campus_edge.repository.ExperienceUpvoteRepository;
import com.divyanka.campus_edge.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ExperienceUpvoteRepository upvoteRepository;
    private final UserRepository userRepository;

    public ExperienceService(ExperienceRepository experienceRepository,
                              ExperienceUpvoteRepository upvoteRepository,
                              UserRepository userRepository) {
        this.experienceRepository = experienceRepository;
        this.upvoteRepository = upvoteRepository;
        this.userRepository = userRepository;
    }

    public ExperienceResponse createExperience(String userEmail, CreateExperienceRequest req) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BadRequestException("User not found"));

        Experience exp = new Experience();
        exp.setPostedBy(user);
        exp.setCompanyName(req.getCompanyName());
        exp.setRoleOffered(req.getRoleOffered());
        exp.setOnCampus(req.getOnCampus());
        exp.setInterviewYear(req.getInterviewYear());
        exp.setCtcRange(req.getCtcRange());
        exp.setSelectionRatio(req.getSelectionRatio());
        exp.setTotalRounds(req.getTotalRounds());
        exp.setRoundsBreakdown(req.getRoundsBreakdown());
        exp.setQuestionsAsked(req.getQuestionsAsked());
        exp.setTopicsTested(req.getTopicsTested());
        exp.setDifficultyRating(req.getDifficultyRating());
        exp.setPrepDuration(req.getPrepDuration());
        exp.setPrepResources(req.getPrepResources());
        exp.setCgpaAtPlacement(req.getCgpaAtPlacement());
        exp.setBacklogStatus(req.getBacklogStatus());
        exp.setAdviceFocus(req.getAdviceFocus());
        exp.setMistakesToAvoid(req.getMistakesToAvoid());
        exp.setHrTips(req.getHrTips());
        exp.setOverallExperience(req.getOverallExperience());
        exp.setIsAnonymous(req.getIsAnonymous() != null ? req.getIsAnonymous() : false);

        Experience saved = experienceRepository.save(exp);
        return toResponse(saved);
    }

    public List<ExperienceResponse> getExperiences(String company, Integer year) {
        return experienceRepository.findWithFilters(
                (company == null || company.isBlank()) ? null : company,
                year
        ).stream().map(this::toResponse).toList();
    }

    public void upvoteExperience(Long experienceId, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BadRequestException("User not found"));

        Experience exp = experienceRepository.findById(experienceId)
                .orElseThrow(() -> new BadRequestException("Experience not found"));

        if (upvoteRepository.existsByExperienceIdAndUserId(experienceId, user.getId())) {
            throw new BadRequestException("You already upvoted this experience");
        }

        ExperienceUpvote upvote = new ExperienceUpvote();
        upvote.setExperience(exp);
        upvote.setUser(user);
        upvoteRepository.save(upvote);

        exp.setUpvoteCount(exp.getUpvoteCount() + 1);
        experienceRepository.save(exp);
    }

    private ExperienceResponse toResponse(Experience exp) {
        String displayName = exp.getIsAnonymous() ? "Anonymous Senior" : exp.getPostedBy().getName();
        return new ExperienceResponse(
                exp.getId(), displayName, exp.getCompanyName(), exp.getRoleOffered(),
                exp.getOnCampus(), exp.getInterviewYear(), exp.getCtcRange(), exp.getSelectionRatio(),
                exp.getTotalRounds(), exp.getRoundsBreakdown(), exp.getQuestionsAsked(),
                exp.getTopicsTested(), exp.getDifficultyRating(), exp.getPrepDuration(),
                exp.getPrepResources(), exp.getCgpaAtPlacement(), exp.getBacklogStatus(),
                exp.getAdviceFocus(), exp.getMistakesToAvoid(), exp.getHrTips(),
                exp.getOverallExperience(), exp.getUpvoteCount(), exp.getCreatedAt()
        );
    }
}