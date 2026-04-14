package com.upiiz.platform_api.dto;

import com.upiiz.platform_api.models.TopicInterestSourceType;

import java.util.UUID;

public class CreateTopicInterestEventRequest {

    private UUID userId;
    private Long categoryId;
    private Long subareaId;
    private Long threadId;
    private UUID appointmentId;
    private UUID videoMeetingId;
    private TopicInterestSourceType sourceType;
    private Integer weight;

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
}