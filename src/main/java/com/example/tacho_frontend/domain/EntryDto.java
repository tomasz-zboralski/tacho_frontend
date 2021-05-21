package com.example.tacho_frontend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EntryDto {

    private Long entryId;
    private String type;
    private String time;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration duration;
    private AssignmentDto assignmentDto;

    public EntryDto(Long entryId, String type, LocalDateTime startTime, LocalDateTime endTime) {
        this.entryId = entryId;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = Duration.between(startTime, endTime);
    }

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public String getType() {
        return type;
    }

    public String getTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        return "" + startTime.format(format) + " - " + endTime.format(format);
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public long getDuration() {

        return duration.toMinutes();
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public AssignmentDto getAssignmentDto() {
        return assignmentDto;
    }

    public void setAssignmentDto(AssignmentDto assignmentDto) {
        this.assignmentDto = assignmentDto;
    }

}
