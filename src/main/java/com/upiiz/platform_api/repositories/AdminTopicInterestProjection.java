package com.upiiz.platform_api.repositories;

import java.time.LocalDateTime;

public interface AdminTopicInterestProjection {
    Long getCategoryId();
    String getCategoryCode();
    String getCategoryName();
    Long getSubareaId();
    String getSubareaName();
    Long getTotalEvents();
    Long getWeightedScore();
    Long getUniqueUsers();
    LocalDateTime getLastEventAt();
}