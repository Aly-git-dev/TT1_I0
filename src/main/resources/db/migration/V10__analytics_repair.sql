-- V10__analytics_repair.sql
-- Reparación de analítica en servidor

-- 1) EXTENSIÓN
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- 2) TABLAS (por si no existen)
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
    created_at TIMESTAMP DEFAULT NOW()
    );

-- 3) VISTAS ADMIN
CREATE OR REPLACE VIEW vw_admin_topic_interest AS
SELECT
    fc.id AS category_id,
    fc.name AS category_name,
    COUNT(tie.id) AS total_events,
    COALESCE(SUM(tie.weight), 0) AS weighted_score
FROM topic_interest_event tie
         JOIN forum_category fc ON fc.id = tie.category_id
GROUP BY fc.id, fc.name;

CREATE OR REPLACE VIEW vw_admin_topic_difficulty AS
SELECT
    fc.id AS category_id,
    fc.name AS category_name,
    COUNT(tde.id) AS total_reports,
    COALESCE(AVG(tde.difficulty_level), 0) AS avg_difficulty
FROM topic_difficulty_event tde
         JOIN forum_category fc ON fc.id = tde.category_id
GROUP BY fc.id, fc.name;

-- 4) VISTA PERFORMANCE DOCENTE (simplificada y segura)
CREATE OR REPLACE VIEW vw_teacher_performance AS
SELECT
    u.id AS teacher_id,
    u.full_name,
    COUNT(te.id) AS total_evaluations,
    COALESCE(AVG(te.rating_clarity), 0) AS avg_clarity,
    COALESCE(AVG(te.rating_knowledge), 0) AS avg_knowledge,
    COALESCE(AVG(te.rating_support), 0) AS avg_support,
    COALESCE(AVG(te.rating_punctuality), 0) AS avg_punctuality
FROM users u
         LEFT JOIN teacher_evaluation te ON te.teacher_id = u.id
GROUP BY u.id, u.full_name;

-- 5) ÁREAS DE MEJORA
CREATE OR REPLACE VIEW vw_teacher_improvement_areas AS
SELECT
    tde.teacher_id,
    fc.id AS category_id,
    fc.name AS category_name,
    COUNT(tde.id) AS total_events,
    COALESCE(AVG(tde.difficulty_level), 0) AS avg_difficulty
FROM topic_difficulty_event tde
         JOIN forum_category fc ON fc.id = tde.category_id
WHERE tde.teacher_id IS NOT NULL
GROUP BY tde.teacher_id, fc.id, fc.name;