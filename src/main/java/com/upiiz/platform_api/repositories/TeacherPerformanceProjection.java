package com.upiiz.platform_api.repositories;

import java.math.BigDecimal;
import java.util.UUID;

public interface TeacherPerformanceProjection {
    UUID getTeacherId();
    String getFullName();
    String getEmailInst();

    Long getTotalEvaluations();
    BigDecimal getAvgClarity();
    BigDecimal getAvgKnowledge();
    BigDecimal getAvgSupport();
    BigDecimal getAvgPunctuality();
    BigDecimal getAvgGlobalScore();

    Long getTotalForumPosts();
    Long getTotalForumThreads();
    Long getTotalAppointmentsCreated();
    Long getCompletedAppointments();
    Long getTotalVideoMeetings();
    Long getEndedVideoMeetings();
}