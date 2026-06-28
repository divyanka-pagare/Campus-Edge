package com.divyanka.campus_edge.repository;

import com.divyanka.campus_edge.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    @Query("SELECT e FROM Experience e WHERE e.isApproved = true " +
           "AND (:company IS NULL OR LOWER(e.companyName) LIKE LOWER(CONCAT('%', :company, '%'))) " +
           "AND (:year IS NULL OR e.interviewYear = :year) " +
           "ORDER BY e.upvoteCount DESC, e.createdAt DESC")
    List<Experience> findWithFilters(String company, Integer year);
}