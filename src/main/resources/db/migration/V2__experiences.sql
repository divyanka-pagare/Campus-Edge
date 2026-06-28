CREATE TABLE experiences (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    posted_by BIGINT NOT NULL,
    company_name VARCHAR(150) NOT NULL,
    role_offered VARCHAR(150) NOT NULL,
    on_campus BOOLEAN NOT NULL DEFAULT TRUE,
    interview_year INT NOT NULL,
    ctc_range VARCHAR(50),
    selection_ratio VARCHAR(100),
    total_rounds INT NOT NULL,
    rounds_breakdown TEXT NOT NULL,
    questions_asked TEXT NOT NULL,
    topics_tested VARCHAR(500),
    difficulty_rating INT,
    prep_duration VARCHAR(50),
    prep_resources TEXT,
    cgpa_at_placement VARCHAR(20),
    backlog_status VARCHAR(50),
    advice_focus TEXT,
    mistakes_to_avoid TEXT,
    hr_tips TEXT,
    overall_experience VARCHAR(50),
    is_anonymous BOOLEAN NOT NULL DEFAULT FALSE,
    upvote_count INT NOT NULL DEFAULT 0,
    is_approved BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_experience_user FOREIGN KEY (posted_by) REFERENCES users(id)
);

CREATE INDEX idx_experiences_company ON experiences(company_name);
CREATE INDEX idx_experiences_year ON experiences(interview_year);
CREATE TABLE experience_upvotes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    experience_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_upvote_experience FOREIGN KEY (experience_id) REFERENCES experiences(id),
    CONSTRAINT fk_upvote_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT unique_user_experience_upvote UNIQUE (experience_id, user_id)
);