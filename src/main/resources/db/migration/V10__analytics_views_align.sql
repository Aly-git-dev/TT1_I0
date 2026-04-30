-- V10__analytics_views_align.sql

DROP VIEW IF EXISTS vw_teacher_improvement_areas CASCADE;
DROP VIEW IF EXISTS vw_teacher_performance CASCADE;
DROP VIEW IF EXISTS vw_admin_topic_difficulty CASCADE;
DROP VIEW IF EXISTS vw_admin_topic_interest CASCADE;

-- INTERÉS
CREATE VIEW vw_admin_topic_interest AS
SELECT
    fc.id AS category_id,
    fc.name AS category_name,
    fc.name AS category_code,
    NULL::bigint AS subarea_id,
    NULL::text AS subarea_name,
    COUNT(tie.id) AS total_events,
    COALESCE(SUM(tie.weight), 0) AS weighted_score,
    COUNT(DISTINCT tie.user_id) AS unique_users,
    MAX(tie.created_at) AS last_event_at
FROM topic_interest_event tie
         JOIN forum_category fc ON fc.id = tie.category_id
GROUP BY fc.id, fc.name;

-- DIFICULTAD
CREATE VIEW vw_admin_topic_difficulty AS
SELECT
    fc.id AS category_id,
    fc.name AS category_name,
    fc.name AS category_code,
    NULL::bigint AS subarea_id,
    NULL::text AS subarea_name,
    COUNT(tde.id) AS total_reports,
    COALESCE(AVG(tde.difficulty_level), 0) AS avg_difficulty,
    COUNT(DISTINCT tde.user_id) AS affected_students,
    MAX(tde.created_at) AS last_report_at
FROM topic_difficulty_event tde
         JOIN forum_category fc ON fc.id = tde.category_id
GROUP BY fc.id, fc.name;

-- PERFORMANCE
CREATE VIEW vw_teacher_performance AS
SELECT
    u.id AS teacher_id,
    u.full_name,
    u.email_inst,
    COUNT(te.id) AS total_evaluations,
    COALESCE(AVG(te.rating_clarity), 0) AS avg_clarity,
    COALESCE(AVG(te.rating_knowledge), 0) AS avg_knowledge,
    COALESCE(AVG(te.rating_support), 0) AS avg_support,
    COALESCE(AVG(te.rating_punctuality), 0) AS avg_punctuality,
    COALESCE((
                 AVG(te.rating_clarity)
                     + AVG(te.rating_knowledge)
                     + AVG(te.rating_support)
                     + AVG(te.rating_punctuality)
                 ) / 4.0, 0) AS avg_global_score,
    0 AS total_forum_posts,
    0 AS total_forum_threads,
    0 AS total_appointments_created,
    0 AS completed_appointments,
    0 AS total_video_meetings,
    0 AS ended_video_meetings
FROM users u
         LEFT JOIN teacher_evaluation te ON te.teacher_id = u.id
GROUP BY u.id, u.full_name, u.email_inst;

-- ÁREAS DE MEJORA
CREATE VIEW vw_teacher_improvement_areas AS
SELECT
    tde.teacher_id,
    u.full_name AS teacher_name,
    fc.id AS category_id,
    fc.name AS category_name,
    fc.name AS category_code,
    NULL::bigint AS subarea_id,
    NULL::text AS subarea_name,
    COUNT(tde.id) AS total_difficulty_events,
    COALESCE(AVG(tde.difficulty_level), 0) AS avg_difficulty,
    MAX(tde.created_at) AS last_event_at
FROM topic_difficulty_event tde
         JOIN users u ON u.id = tde.teacher_id
         JOIN forum_category fc ON fc.id = tde.category_id
WHERE tde.teacher_id IS NOT NULL
GROUP BY tde.teacher_id, u.full_name, fc.id, fc.name;