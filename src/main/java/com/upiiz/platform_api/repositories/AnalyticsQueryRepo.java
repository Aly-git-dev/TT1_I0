package com.upiiz.platform_api.repositories;

import com.upiiz.platform_api.entities.TeacherEvaluation;
import com.upiiz.platform_api.repositories.AdminTopicDifficultyProjection;
import com.upiiz.platform_api.repositories.AdminTopicInterestProjection;
import com.upiiz.platform_api.repositories.TeacherImprovementAreaProjection;
import com.upiiz.platform_api.repositories.TeacherPerformanceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AnalyticsQueryRepo extends JpaRepository<TeacherEvaluation, UUID> {

    @Query(value = """
        SELECT *
        FROM vw_admin_topic_interest
        ORDER BY weighted_score DESC, total_events DESC
        """, nativeQuery = true)
    List<AdminTopicInterestProjection> findAdminTopicInterest();

    @Query(value = """
        SELECT *
        FROM vw_admin_topic_difficulty
        ORDER BY avg_difficulty DESC, total_reports DESC
        """, nativeQuery = true)
    List<AdminTopicDifficultyProjection> findAdminTopicDifficulty();

    @Query(value = """
        SELECT *
        FROM vw_teacher_performance
        ORDER BY avg_global_score DESC, total_evaluations DESC
        """, nativeQuery = true)
    List<TeacherPerformanceProjection> findTeacherPerformance();

    @Query(value = """
        SELECT *
        FROM vw_teacher_performance
        WHERE teacher_id = :teacherId
        """, nativeQuery = true)
    TeacherPerformanceProjection findTeacherPerformanceByTeacherId(@Param("teacherId") UUID teacherId);

    @Query(value = """
        SELECT *
        FROM vw_teacher_improvement_areas
        ORDER BY avg_difficulty DESC, total_difficulty_events DESC
        """, nativeQuery = true)
    List<TeacherImprovementAreaProjection> findTeacherImprovementAreas();

    @Query(value = """
        SELECT *
        FROM vw_teacher_improvement_areas
        WHERE teacher_id = :teacherId
        ORDER BY avg_difficulty DESC, total_difficulty_events DESC
        """, nativeQuery = true)
    List<TeacherImprovementAreaProjection> findTeacherImprovementAreasByTeacherId(@Param("teacherId") UUID teacherId);
}