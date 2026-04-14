package com.upiiz.platform_api.repositories;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface TeacherImprovementAreaProjection {
    UUID getTeacherId();
    String getTeacherName();
    Long getCategoryId();
    String getCategoryCode();
    String getCategoryName();
    Long getSubareaId();
    String getSubareaName();
    Long getTotalDifficultyEvents();
    BigDecimal getAvgDifficulty();
    LocalDateTime getLastEventAt();
}