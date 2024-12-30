package ru.raperan.abstracttaskexecutorservice.masterservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class TaskApiDto {

    private UUID id;
    private String payload;
    private Long ttl;
    private Set<StepApiDto> steps;

}
