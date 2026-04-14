package com.upiiz.platform_api.entities;

import com.upiiz.platform_api.models.TopicDifficultySourceType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "topic_difficulty_event")
public class TopicDifficultyEvent {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "teacher_id")
    private UUID teacherId;

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
    private TopicDifficultySourceType sourceType;

    @Column(name = "difficulty_level", nullable = false)
    private Short difficultyLevel;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public UUID getTeacherId() { return teacherId; }
    public void setTeacherId(UUID teacherId) { this.teacherId = teacherId; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public Long getSubareaId() { return subareaId; }
    public void setSubareaId(Long subareaId) { this.subareaId = subareaId; }

    public Long getThreadId() { return threadId; }
    public void setThreadId(Long threadId) { this.threadId = threadId; }

    public UUID getAppointmentId() { return appointmentId; }
    public void setAppointmentId(UUID appointmentId) { this.appointmentId = appointmentId; }

    public UUID getVideoMeetingId() { return videoMeetingId; }
    public void setVideoMeetingId(UUID videoMeetingId) { this.videoMeetingId = videoMeetingId; }

    public TopicDifficultySourceType getSourceType() { return sourceType; }
    public void setSourceType(TopicDifficultySourceType sourceType) { this.sourceType = sourceType; }

    public Short getDifficultyLevel() { return difficultyLevel; }
    public void setDifficultyLevel(Short difficultyLevel) { this.difficultyLevel = difficultyLevel; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}