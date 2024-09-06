package com.furkan.study_tracker_api.dto;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
//import lombok.Getter;
//import lombok.Setter;
import java.time.LocalDateTime;


public class ActivityDto {


    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;

    @NotNull(message = "Duration must be provided")
    @Min(value = 15, message = "Duration must be at least 15 minutes")
    @Max(value = 240, message = "Duration must be at most 240 minutes")
    private Integer durationMinutes;

    private Boolean successful;

    @NotNull
    private Long createdById;

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public @NotNull(message = "Duration must be provided") @Min(value = 15, message = "Duration must be at least 15 minutes") @Max(value = 240, message = "Duration must be at most 240 minutes") Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(@NotNull(message = "Duration must be provided") @Min(value = 15, message = "Duration must be at least 15 minutes") @Max(value = 240, message = "Duration must be at most 240 minutes") Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    public @NotNull Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(@NotNull Long createdById) {
        this.createdById = createdById;
    }
}
