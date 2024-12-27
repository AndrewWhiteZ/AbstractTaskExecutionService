package ru.raperan.abstracttaskexecutorservice.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.raperan.abstracttaskexecutorservice.common.enums.Status;

import java.util.UUID;

@Data
@AllArgsConstructor
public class StatusUpdateDto {

    private UUID taskId;

    private UUID stepId;

    private Status status;

    private String message;

}
