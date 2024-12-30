package ru.raperan.abstracttaskexecutorservice.masterservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StepApiDto {

    private String name;
    private String status;
    private String payload;
    private String result;
    private LocalDateTime startTime;

}
