package ru.raperan.abstracttaskexecutorservice.masterservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "step")
public class Step {

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "result")
    private Result result;

    @Column(name = "payload")
    private Payload payload;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

}
