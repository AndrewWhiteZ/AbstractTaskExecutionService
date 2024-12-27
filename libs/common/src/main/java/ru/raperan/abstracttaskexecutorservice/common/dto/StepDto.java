package ru.raperan.abstracttaskexecutorservice.common.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class StepDto {

    private UUID id;

    private String name;

    private PayloadDto payload;

}
