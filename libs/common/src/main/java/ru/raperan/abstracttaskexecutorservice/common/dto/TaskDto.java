package ru.raperan.abstracttaskexecutorservice.common.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class TaskDto {

    private UUID id;

    private List<StepDto> steps;

}
