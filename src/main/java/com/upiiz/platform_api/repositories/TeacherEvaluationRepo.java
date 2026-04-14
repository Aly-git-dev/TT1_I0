package com.upiiz.platform_api.repositories;

import com.upiiz.platform_api.entities.TeacherEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeacherEvaluationRepo extends JpaRepository<TeacherEvaluation, UUID> {

    List<TeacherEvaluation> findByTeacherIdOrderByCreatedAtDesc(UUID teacherId);

    Optional<TeacherEvaluation> findByEvaluatorIdAndTeacherIdAndAppointmentId(
            UUID evaluatorId,
            UUID teacherId,
            UUID appointmentId
    );
}