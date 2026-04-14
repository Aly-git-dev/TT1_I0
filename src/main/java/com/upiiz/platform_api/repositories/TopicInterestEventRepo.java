package com.upiiz.platform_api.repositories;

import com.upiiz.platform_api.entities.TopicInterestEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TopicInterestEventRepo extends JpaRepository<TopicInterestEvent, UUID> {
}