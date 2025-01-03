package ru.raperan.abstracttaskexecutorservice.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.raperan.abstracttaskexecutorservice.common.enums.Status;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusUpdateDto {

    private UUID taskId;

    private UUID stepId;

    private Status status;

    private String message;

}
