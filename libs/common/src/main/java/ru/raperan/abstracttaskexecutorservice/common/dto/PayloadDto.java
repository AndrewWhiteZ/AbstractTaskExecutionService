package ru.raperan.abstracttaskexecutorservice.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayloadDto {
    String body;
}
