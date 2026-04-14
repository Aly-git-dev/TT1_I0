package com.upiiz.platform_api.dto;

import java.util.UUID;

public class CreateTeacherEvaluationRequest {

    private UUID teacherId;
    private UUID evaluatorId;
    private UUID appointmentId;

    private Integer ratingClarity;
    private Integer ratingKnowledge;
    private Integer ratingSupport;
    private Integer ratingPunctuality;

    private String comment;
    private Boolean anonymous;

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
    }

    public UUID getEvaluatorId() {
        return evaluatorId;
    }

    public void setEvaluatorId(UUID evaluatorId) {
        this.evaluatorId = evaluatorId;
    }

    public UUID getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(UUID appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Integer getRatingClarity() {
        return ratingClarity;
    }

    public void setRatingClarity(Integer ratingClarity) {
        this.ratingClarity = ratingClarity;
    }

    public Integer getRatingKnowledge() {
        return ratingKnowledge;
    }

    public void setRatingKnowledge(Integer ratingKnowledge) {
        this.ratingKnowledge = ratingKnowledge;
    }

    public Integer getRatingSupport() {
        return ratingSupport;
    }

    public void setRatingSupport(Integer ratingSupport) {
        this.ratingSupport = ratingSupport;
    }

    public Integer getRatingPunctuality() {
        return ratingPunctuality;
    }

    public void setRatingPunctuality(Integer ratingPunctuality) {
        this.ratingPunctuality = ratingPunctuality;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }
}