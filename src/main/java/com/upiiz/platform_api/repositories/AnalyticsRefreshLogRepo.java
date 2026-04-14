package com.upiiz.platform_api.repositories;

import com.upiiz.platform_api.entities.AnalyticsRefreshLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnalyticsRefreshLogRepo extends JpaRepository<AnalyticsRefreshLog, UUID> {
}