package com.upiiz.platform_api.entities;

import com.upiiz.platform_api.models.TopicInterestSourceType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "topic_interest_event")
public class TopicInterestEvent {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "subarea_id")
    private Long subareaId;

    @Column(name = "thread_id")
    private Long threadId;

    @Column(name = "appointment_id")
    private UUID appointmentId;

    @Column(name = "video_meeting_id")
    private UUID videoMeetingId;

    @Enumerated(EnumType.STRING)
    @Column(name = "source_type", nullable = false)
    private TopicInterestSourceType sourceType;

    @Column(name = "weight", nullable = false)
    private Integer weight = 1;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.weight == null) this.weight = 1;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getSubareaId() {
        return subareaId;
    }

    public void setSubareaId(Long subareaId) {
        this.subareaId = subareaId;
    }

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public UUID getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(UUID appointmentId) {
        this.appointmentId = appointmentId;
    }

    public UUID getVideoMeetingId() {
        return videoMeetingId;
    }

    public void setVideoMeetingId(UUID videoMeetingId) {
        this.videoMeetingId = videoMeetingId;
    }

    public TopicInterestSourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(TopicInterestSourceType sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}