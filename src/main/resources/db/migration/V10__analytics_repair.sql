-- V10__analytics_repair.sql

CREATE EXTENSION IF NOT EXISTS pgcrypto;

DROP VIEW IF EXISTS vw_teacher_improvement_areas CASCADE;
DROP VIEW IF EXISTS vw_teacher_performance CASCADE;
DROP VIEW IF EXISTS vw_admin_topic_difficulty CASCADE;
DROP VIEW IF EXISTS vw_admin_topic_interest CASCADE;

CREATE TABLE IF NOT EXISTS teacher_evaluation (
                                                  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    teacher_id UUID NOT NULL,
    evaluator_id UUID,
    appointment_id UUID,
    rating_clarity SMALLINT,
    rating_knowledge SMALLINT,
    rating_support SMALLINT,
    rating_punctuality SMALLINT,
    comment TEXT,
    is_anonymous BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
    );

CREATE TABLE IF NOT EXISTS topic_interest_event (
                                                    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID,
    category_id BIGINT,
    subarea_id BIGINT,
    thread_id BIGINT,
    appointment_id UUID,
    video_meeting_id UUID,
    source_type VARCHAR(30),
    weight INTEGER DEFAULT 1,
    created_at TIMESTAMP DEFAULT NOW()
    );

CREATE TABLE IF NOT EXISTS topic_difficulty_event (
                                                      id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID,
    teacher_id UUID,
    category_id BIGINT,
    subarea_id BIGINT,
    thread_id BIGINT,
    appointment_id UUID,
    video_meeting_id UUID,
    source_type VARCHAR(30),
    difficulty_level SMALLINT,
    notes TEXT,
    created_at TIMESTAMP DEFAULT NOW()
    );

CREATE OR REPLACE VIEW vw_admin_topic_interest AS
SELECT
    fc.id AS category_id,
    COALESCE(fc.code, fc.id::text) AS category_code,
    fc.name AS category_name,
    fs.id AS subarea_id,
    fs.name AS subarea_name,
    COUNT(tie.id) AS total_events,
    COALESCE(SUM(tie.weight), 0) AS weighted_score,
    COUNT(DISTINCT tie.user_id) AS unique_users,
    MAX(tie.created_at) AS last_event_at
FROM topic_interest_event tie
         JOIN forum_category fc ON fc.id = tie.category_id
         LEFT JOIN forum_subarea fs ON fs.id = tie.subarea_id
GROUP BY fc.id, fc.code, fc.name, fs.id, fs.name;

CREATE OR REPLACE VIEW vw_admin_topic_difficulty AS
SELECT
    fc.id AS category_id,
    COALESCE(fc.code, fc.id::text) AS category_code,
    fc.name AS category_name,
    fs.id AS subarea_id,
    fs.name AS subarea_name,
    COUNT(tde.id) AS total_reports,
    COALESCE(ROUND(AVG(tde.difficulty_level)::numeric, 2), 0) AS avg_difficulty,
    COUNT(DISTINCT tde.user_id) AS affected_students,
    MAX(tde.created_at) AS last_report_at
FROM topic_difficulty_event tde
         JOIN forum_category fc ON fc.id = tde.category_id
         LEFT JOIN forum_subarea fs ON fs.id = tde.subarea_id
GROUP BY fc.id, fc.code, fc.name, fs.id, fs.name;

CREATE OR REPLACE VIEW vw_teacher_performance AS
SELECT
    u.id AS teacher_id,
    u.full_name,
    u.email_inst,
    COUNT(te.id) AS total_evaluations,
    COALESCE(ROUND(AVG(te.rating_clarity)::numeric, 2), 0) AS avg_clarity,
    COALESCE(ROUND(AVG(te.rating_knowledge)::numeric, 2), 0) AS avg_knowledge,
    COALESCE(ROUND(AVG(te.rating_support)::numeric, 2), 0) AS avg_support,
    COALESCE(ROUND(AVG(te.rating_punctuality)::numeric, 2), 0) AS avg_punctuality,
    COALESCE(ROUND((
                       AVG(te.rating_clarity)
                           + AVG(te.rating_knowledge)
                           + AVG(te.rating_support)
                           + AVG(te.rating_punctuality)
                       ) / 4.0::numeric, 2), 0) AS avg_global_score,
    0 AS total_forum_posts,
    0 AS total_forum_threads,
    0 AS total_appointments_created,
    0 AS completed_appointments,
    0 AS total_video_meetings,
    0 AS ended_video_meetings
FROM users u
         LEFT JOIN teacher_evaluation te ON te.teacher_id = u.id
GROUP BY u.id, u.full_name, u.email_inst;

CREATE OR REPLACE VIEW vw_teacher_improvement_areas AS
SELECT
    tde.teacher_id,
    u.full_name AS teacher_name,
    fc.id AS category_id,
    COALESCE(fc.code, fc.id::text) AS category_code,
    fc.name AS category_name,
    fs.id AS subarea_id,
    fs.name AS subarea_name,
    COUNT(tde.id) AS total_difficulty_events,
    COALESCE(ROUND(AVG(tde.difficulty_level)::numeric, 2), 0) AS avg_difficulty,
    MAX(tde.created_at) AS last_event_at
FROM topic_difficulty_event tde
         JOIN users u ON u.id = tde.teacher_id
         JOIN forum_category fc ON fc.id = tde.category_id
         LEFT JOIN forum_subarea fs ON fs.id = tde.subarea_id
WHERE tde.teacher_id IS NOT NULL
GROUP BY tde.teacher_id, u.full_name, fc.id, fc.code, fc.name, fs.id, fs.name;