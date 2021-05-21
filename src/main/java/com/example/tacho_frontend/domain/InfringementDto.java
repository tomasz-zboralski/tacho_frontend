package com.example.tacho_frontend.domain;

import lombok.AllArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@AllArgsConstructor
public class InfringementDto {

    private Long infringementId;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration duration;
    private AssignmentDto assignmentDto;

}
