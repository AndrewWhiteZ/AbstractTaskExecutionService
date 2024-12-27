package ru.raperan.abstracttaskexecutorservice.masterservice.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class TaskDto {

    private UUID id;
    private String payload;
    private Long ttl;
    private List<StepDto> steps;

}
