package com.divyanka.campus_edge.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "batch_year")
    private Integer batchYear;

    private String branch;

    private java.math.BigDecimal cgpa;

    @Column(name = "linkedin_url")
    private String linkedinUrl;

    @Column(name = "is_verified")
    private Boolean isVerified = false;

    @Column(name = "is_email_verified")
    private Boolean isEmailVerified = false;

    @Column(name = "profile_complete_pct")
    private Integer profileCompletePct = 0;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}