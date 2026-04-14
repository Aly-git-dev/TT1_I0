package com.upiiz.platform_api.dto;

import com.upiiz.platform_api.models.TopicDifficultySourceType;

import java.util.UUID;

public class CreateTopicDifficultyEventRequest {

    private UUID userId;
    private UUID teacherId;
    private Long categoryId;
    private Long subareaId;
    private Long threadId;
    private UUID appointmentId;
    private UUID videoMeetingId;
    private TopicDifficultySourceType sourceType;
    private Integer difficultyLevel;
    private String notes;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
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

    public TopicDifficultySourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(TopicDifficultySourceType sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Integer difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}