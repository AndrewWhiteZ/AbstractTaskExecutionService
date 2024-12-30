package ru.raperan.abstracttaskexecutorservice.masterservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StepDto {

    private String name;
    private String status;
    private String payload;
    private String response;
    private LocalDateTime startTime;

}
