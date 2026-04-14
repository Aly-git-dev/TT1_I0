package com.upiiz.platform_api.repositories;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AdminTopicDifficultyProjection {
    Long getCategoryId();
    String getCategoryCode();
    String getCategoryName();
    Long getSubareaId();
    String getSubareaName();
    Long getTotalReports();
    BigDecimal getAvgDifficulty();
    Long getAffectedStudents();
    LocalDateTime getLastReportAt();
}