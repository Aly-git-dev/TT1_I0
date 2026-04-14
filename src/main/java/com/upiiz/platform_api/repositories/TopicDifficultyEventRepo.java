package com.upiiz.platform_api.repositories;

import com.upiiz.platform_api.entities.TopicDifficultyEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TopicDifficultyEventRepo extends JpaRepository<TopicDifficultyEvent, UUID> {
}