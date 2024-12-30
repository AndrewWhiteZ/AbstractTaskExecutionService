package ru.raperan.abstracttaskexecutorservice.common.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class StepDto {

    private UUID id;

    private String name;

    private PayloadDto payload;

}
