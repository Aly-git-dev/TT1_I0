package com.upiiz.platform_api.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "teacher_evaluation")
public class TeacherEvaluation {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "teacher_id", nullable = false)
    private UUID teacherId;

    @Column(name = "evaluator_id")
    private UUID evaluatorId;

    @Column(name = "appointment_id")
    private UUID appointmentId;

    @Column(name = "rating_clarity", nullable = false)
    private Short ratingClarity;

    @Column(name = "rating_knowledge", nullable = false)
    private Short ratingKnowledge;

    @Column(name = "rating_support", nullable = false)
    private Short ratingSupport;

    @Column(name = "rating_punctuality", nullable = false)
    private Short ratingPunctuality;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "is_anonymous", nullable = false)
    private Boolean anonymous = false;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        if (this.anonymous == null) this.anonymous = false;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getTeacherId() { return teacherId; }
    public void setTeacherId(UUID teacherId) { this.teacherId = teacherId; }

    public UUID getEvaluatorId() { return evaluatorId; }
    public void setEvaluatorId(UUID evaluatorId) { this.evaluatorId = evaluatorId; }

    public UUID getAppointmentId() { return appointmentId; }
    public void setAppointmentId(UUID appointmentId) { this.appointmentId = appointmentId; }

    public Short getRatingClarity() { return ratingClarity; }
    public void setRatingClarity(Short ratingClarity) { this.ratingClarity = ratingClarity; }

    public Short getRatingKnowledge() { return ratingKnowledge; }
    public void setRatingKnowledge(Short ratingKnowledge) { this.ratingKnowledge = ratingKnowledge; }

    public Short getRatingSupport() { return ratingSupport; }
    public void setRatingSupport(Short ratingSupport) { this.ratingSupport = ratingSupport; }

    public Short getRatingPunctuality() { return ratingPunctuality; }
    public void setRatingPunctuality(Short ratingPunctuality) { this.ratingPunctuality = ratingPunctuality; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Boolean getAnonymous() { return anonymous; }
    public void setAnonymous(Boolean anonymous) { this.anonymous = anonymous; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}