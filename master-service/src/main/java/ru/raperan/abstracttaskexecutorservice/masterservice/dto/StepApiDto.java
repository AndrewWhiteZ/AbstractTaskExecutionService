package ru.raperan.abstracttaskexecutorservice.masterservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StepApiDto {

    private String name;
    private String status;
    private String payload;
    private LocalDateTime startTime;

    public String getPayload() {
        return (payload == null) ? "" : payload;
    }

    public LocalDateTime getStartTime() {
        return (startTime == null) ? LocalDateTime.now() : startTime;
    }

}
