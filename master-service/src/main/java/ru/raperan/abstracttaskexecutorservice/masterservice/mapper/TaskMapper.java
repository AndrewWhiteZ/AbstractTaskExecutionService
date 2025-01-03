package ru.raperan.abstracttaskexecutorservice.masterservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.raperan.abstracttaskexecutorservice.common.dto.TaskDto;
import ru.raperan.abstracttaskexecutorservice.masterservice.dto.TaskApiDto;
import ru.raperan.abstracttaskexecutorservice.masterservice.entity.Payload;
import ru.raperan.abstracttaskexecutorservice.masterservice.entity.Task;
import ru.raperan.abstracttaskexecutorservice.masterservice.web.request.CreateTaskRequest;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final StepMapper stepMapper;

    public Task mapRequestToEntity(CreateTaskRequest request) {
        Task task = Task.builder()
                .ttl(request.getTtl())
                .steps(request.getSteps().stream().map(
                                stepMapper::mapRequestToEntity
                        ).
                        collect(Collectors.toList()))
                .build();
        task.getSteps()
                .stream()
                .findFirst()
                .ifPresent(step -> step.setPayload(Payload.builder()
                        .body(request.getPayload())
                        .build()));
        return task;
    }

    public TaskDto mapEntityToDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .ttl(task.getTtl())
                .steps(task.getSteps().stream().map(
                        stepMapper::mapEntityToDto
                ).collect(Collectors.toList()))
                .build();
    }

    public TaskApiDto mapEntityToApiDto(Task task) {
        return TaskApiDto.builder()
                .id(task.getId())
                .ttl(task.getTtl())
                .steps(task.getSteps().stream().map(
                        stepMapper::mapEntityToApiDto).collect(Collectors.toList())
                )
                .build();
    }

}
