package ru.raperan.abstracttaskexecutorservice.masterservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class TaskApiDto {

    private UUID id;
    private String payload;
    private Long ttl;
    private List<StepApiDto> steps;

}
