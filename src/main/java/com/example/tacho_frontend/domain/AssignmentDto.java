package com.example.tacho_frontend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AssignmentDto {
    private Long assignmentId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration duration;
    private DutyDto duty;
    private String driver;
    private String invoice;
    private String time;
    private List<EntryDto> entries;
    private Set<InfringementDto> infringements;

    public AssignmentDto(Long assignmentId, LocalDateTime startTime, LocalDateTime endTime, DutyDto duty, List<EntryDto> entries) {
        this.assignmentId = assignmentId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duty = duty;
        this.entries = entries;
        this.duration = Duration.between(startTime, endTime);
    }

    public AssignmentDto() {
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public DutyDto getDuty() {
        return duty;
    }

    public String getDriver() {
        return driver;
    }

    public String getTime() {
        if (!entries.isEmpty()){
            String startTime = entries.get(0).getStartTime().toString();
            String endTime = entries.get(entries.size() - 1).getEndTime().toString();
            return startTime + " - " + endTime;
        }
        return "Add entries to get time";
    }

    public List<EntryDto> getEntries() {
        return entries;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setDuty(DutyDto duty) {
        this.duty = duty;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setEntries(List<EntryDto> entries) {
        this.entries = entries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssignmentDto)) return false;

        AssignmentDto that = (AssignmentDto) o;

        if (getAssignmentId() != null ? !getAssignmentId().equals(that.getAssignmentId()) : that.getAssignmentId() != null)
            return false;
        if (getStartTime() != null ? !getStartTime().equals(that.getStartTime()) : that.getStartTime() != null)
            return false;
        if (getEndTime() != null ? !getEndTime().equals(that.getEndTime()) : that.getEndTime() != null) return false;
        if (getDuration() != null ? !getDuration().equals(that.getDuration()) : that.getDuration() != null)
            return false;
        if (getDuty() != null ? !getDuty().equals(that.getDuty()) : that.getDuty() != null) return false;
        if (getDriver() != null ? !getDriver().equals(that.getDriver()) : that.getDriver() != null) return false;
        if (invoice != null ? !invoice.equals(that.invoice) : that.invoice != null) return false;
        if (getEntries() != null ? !getEntries().equals(that.getEntries()) : that.getEntries() != null) return false;
        return infringements != null ? infringements.equals(that.infringements) : that.infringements == null;
    }

    @Override
    public int hashCode() {
        int result = getAssignmentId() != null ? getAssignmentId().hashCode() : 0;
        result = 31 * result + (getStartTime() != null ? getStartTime().hashCode() : 0);
        result = 31 * result + (getEndTime() != null ? getEndTime().hashCode() : 0);
        result = 31 * result + (getDuration() != null ? getDuration().hashCode() : 0);
        result = 31 * result + (getDuty() != null ? getDuty().hashCode() : 0);
        result = 31 * result + (getDriver() != null ? getDriver().hashCode() : 0);
        result = 31 * result + (invoice != null ? invoice.hashCode() : 0);
        result = 31 * result + (getEntries() != null ? getEntries().hashCode() : 0);
        result = 31 * result + (infringements != null ? infringements.hashCode() : 0);
        return result;
    }
}
