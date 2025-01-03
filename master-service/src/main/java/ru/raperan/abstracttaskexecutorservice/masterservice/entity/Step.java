package ru.raperan.abstracttaskexecutorservice.masterservice.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.raperan.abstracttaskexecutorservice.common.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@Table(name = "step")
@AllArgsConstructor
public class Step {

    @Column(name = "name")
    private String name;

    @Column(name = "payload")
    private Payload payload;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @ManyToOne()
    @JoinColumn(name = "task_id")
    private Task task;

    @EmbeddedId
    StepId stepId;

    public String getPayloadAsString() {
        return (payload == null) ? "" : payload.body;
    }

    public LocalDateTime getStartTime() {
        return (startTime == null) ? LocalDateTime.of(1970, 1, 1, 0, 0, 0) : startTime;
    }

}
