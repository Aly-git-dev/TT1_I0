package com.upiiz.platform_api.controller;

import com.upiiz.platform_api.dto.ApiResponse;
import com.upiiz.platform_api.repositories.AdminTopicDifficultyProjection;
import com.upiiz.platform_api.repositories.AdminTopicInterestProjection;
import com.upiiz.platform_api.repositories.TeacherImprovementAreaProjection;
import com.upiiz.platform_api.repositories.TeacherPerformanceProjection;
import com.upiiz.platform_api.services.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/upiiz/admin/v1/analytics")
public class AnalyticsAdminController {

    private final AnalyticsService analyticsService;

    public AnalyticsAdminController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/general/interest")
    public ResponseEntity<ApiResponse<List<AdminTopicInterestProjection>>> getGeneralInterest() {
        return ResponseEntity.ok(
                ApiResponse.success(
                        "Analítica general de interés obtenida correctamente",
                        analyticsService.getAdminTopicInterest()
                )
        );
    }

    @GetMapping("/general/difficulty")
    public ResponseEntity<ApiResponse<List<AdminTopicDifficultyProjection>>> getGeneralDifficulty() {
        return ResponseEntity.ok(
                ApiResponse.success(
                        "Analítica general de dificultad obtenida correctamente",
                        analyticsService.getAdminTopicDifficulty()
                )
        );
    }

    @GetMapping("/teachers/performance")
    public ResponseEntity<ApiResponse<List<TeacherPerformanceProjection>>> getTeacherPerformance() {
        return ResponseEntity.ok(
                ApiResponse.success(
                        "Desempeño docente obtenido correctamente",
                        analyticsService.getTeacherPerformance()
                )
        );
    }

    @GetMapping("/teachers/{teacherId}/performance")
    public ResponseEntity<ApiResponse<TeacherPerformanceProjection>> getTeacherPerformanceByTeacher(
            @PathVariable UUID teacherId
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        "Desempeño docente obtenido correctamente",
                        analyticsService.getTeacherPerformanceByTeacherId(teacherId)
                )
        );
    }

    @GetMapping("/teachers/improvement-areas")
    public ResponseEntity<ApiResponse<List<TeacherImprovementAreaProjection>>> getTeacherImprovementAreas() {
        return ResponseEntity.ok(
                ApiResponse.success(
                        "Áreas de mejora obtenidas correctamente",
                        analyticsService.getTeacherImprovementAreas()
                )
        );
    }

    @GetMapping("/teachers/{teacherId}/improvement-areas")
    public ResponseEntity<ApiResponse<List<TeacherImprovementAreaProjection>>> getTeacherImprovementAreasByTeacher(
            @PathVariable UUID teacherId
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        "Áreas de mejora del docente obtenidas correctamente",
                        analyticsService.getTeacherImprovementAreasByTeacherId(teacherId)
                )
        );
    }
}