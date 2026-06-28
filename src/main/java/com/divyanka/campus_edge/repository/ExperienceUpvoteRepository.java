package com.divyanka.campus_edge.repository;

import com.divyanka.campus_edge.entity.ExperienceUpvote;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ExperienceUpvoteRepository extends JpaRepository<ExperienceUpvote, Long> {
    Optional<ExperienceUpvote> findByExperienceIdAndUserId(Long experienceId, Long userId);
    boolean existsByExperienceIdAndUserId(Long experienceId, Long userId);
}