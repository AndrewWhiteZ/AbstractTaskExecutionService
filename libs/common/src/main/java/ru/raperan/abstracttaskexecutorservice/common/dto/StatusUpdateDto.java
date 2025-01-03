package ru.raperan.abstracttaskexecutorservice.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.raperan.abstracttaskexecutorservice.common.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class StatusUpdateDto {

    private UUID taskId;

    private UUID stepId;

    private String name;

    private String status;

    private String message;

    private LocalDateTime startTime;

    public StatusUpdateDto(UUID taskId, UUID stepId, String name, Status status, String message, LocalDateTime startTime) {
        this.taskId = taskId;
        this.stepId = stepId;
        this.name = name;
        this.status = status.name();
        this.message = message;
        this.startTime = startTime;
    }

    public static StatusUpdateDto updateNow(UUID taskId, UUID stepId, String name, Status status, String message) {
        return new StatusUpdateDto(taskId, stepId, name, status, message, LocalDateTime.now());
    }

    public void setStatus(Status status) {
        this.status = status.name();
    }

    public String getStatus() {
        return status;
    }

}
