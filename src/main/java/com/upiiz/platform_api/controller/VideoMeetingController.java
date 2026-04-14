package com.upiiz.platform_api.controller;

import com.upiiz.platform_api.dto.CancelVideoMeetingRequest;
import com.upiiz.platform_api.dto.CreateVideoMeetingRequest;
import com.upiiz.platform_api.dto.JoinVideoMeetingResponse;
import com.upiiz.platform_api.entities.User;
import com.upiiz.platform_api.entities.VideoMeeting;
import com.upiiz.platform_api.repositories.UserRepository;
import com.upiiz.platform_api.services.VideoMeetingService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/upiiz/public/v1/video-meetings")
@RequiredArgsConstructor
public class VideoMeetingController {

    private final VideoMeetingService videoMeetingService;
    private final UserRepository userRepository;

    private UUID getCurrentUserId(Authentication authentication) {
        String email = authentication.getName();

        User user = userRepository.findByEmailInst(email)
                .orElseThrow(() ->
                        new EntityNotFoundException("Usuario autenticado no encontrado con email: " + email)
                );

        return user.getId();
    }

    @PostMapping
    public VideoMeeting create(
            @Valid @RequestBody CreateVideoMeetingRequest request,
            Authentication authentication
    ) {
        UUID currentUserId = getCurrentUserId(authentication);
        return videoMeetingService.create(request, currentUserId);
    }

    @GetMapping("/{id}")
    public VideoMeeting getById(
            @PathVariable UUID id,
            Authentication authentication
    ) {
        UUID currentUserId = getCurrentUserId(authentication);
        return videoMeetingService.getById(id, currentUserId);
    }

    @GetMapping("/by-appointment/{appointmentId}")
    public VideoMeeting getByAppointment(
            @PathVariable UUID appointmentId,
            Authentication authentication
    ) {
        UUID currentUserId = getCurrentUserId(authentication);
        return videoMeetingService.getByAppointment(appointmentId, currentUserId);
    }

    @GetMapping("/mine")
    public List<VideoMeeting> getMine(Authentication authentication) {
        UUID currentUserId = getCurrentUserId(authentication);
        return videoMeetingService.getMine(currentUserId);
    }

    @PostMapping("/{id}/join")
    public JoinVideoMeetingResponse join(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "Usuario") String displayName,
            @RequestParam(required = false) String deviceInfo,
            Authentication authentication
    ) {
        UUID currentUserId = getCurrentUserId(authentication);
        return videoMeetingService.join(id, currentUserId, displayName, deviceInfo);
    }

    @PostMapping("/{id}/leave")
    public void leave(
            @PathVariable UUID id,
            Authentication authentication
    ) {
        UUID currentUserId = getCurrentUserId(authentication);
        videoMeetingService.leave(id, currentUserId);
    }

    @PatchMapping("/{id}/cancel")
    public VideoMeeting cancel(
            @PathVariable UUID id,
            @Valid @RequestBody CancelVideoMeetingRequest request,
            Authentication authentication
    ) {
        UUID currentUserId = getCurrentUserId(authentication);
        return videoMeetingService.cancel(id, request, currentUserId);
    }
}