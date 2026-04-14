package com.upiiz.platform_api.controller;

import com.upiiz.platform_api.dto.ApiResponse;
import com.upiiz.platform_api.dto.CreateTeacherEvaluationRequest;
import com.upiiz.platform_api.dto.CreateTopicDifficultyEventRequest;
import com.upiiz.platform_api.dto.CreateTopicInterestEventRequest;
import com.upiiz.platform_api.entities.TeacherEvaluation;
import com.upiiz.platform_api.entities.TopicDifficultyEvent;
import com.upiiz.platform_api.entities.TopicInterestEvent;
import com.upiiz.platform_api.services.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/upiiz/private/v1/analytics")
public class AnalyticsInteractionController {

    private final AnalyticsService analyticsService;

    public AnalyticsInteractionController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @PostMapping("/teacher-evaluations")
    public ResponseEntity<ApiResponse<TeacherEvaluation>> createTeacherEvaluation(
            @RequestBody CreateTeacherEvaluationRequest request
    ) {
        TeacherEvaluation saved = analyticsService.createTeacherEvaluation(request);
        return ResponseEntity.ok(
                ApiResponse.success("Evaluación docente registrada correctamente", saved)
        );
    }

    @PostMapping("/topic-interest-events")
    public ResponseEntity<ApiResponse<TopicInterestEvent>> createTopicInterestEvent(
            @RequestBody CreateTopicInterestEventRequest request
    ) {
        TopicInterestEvent saved = analyticsService.createTopicInterestEvent(request);
        return ResponseEntity.ok(
                ApiResponse.success("Evento de interés registrado correctamente", saved)
        );
    }

    @PostMapping("/topic-difficulty-events")
    public ResponseEntity<ApiResponse<TopicDifficultyEvent>> createTopicDifficultyEvent(
            @RequestBody CreateTopicDifficultyEventRequest request
    ) {
        TopicDifficultyEvent saved = analyticsService.createTopicDifficultyEvent(request);
        return ResponseEntity.ok(
                ApiResponse.success("Evento de dificultad registrado correctamente", saved)
        );
    }
}