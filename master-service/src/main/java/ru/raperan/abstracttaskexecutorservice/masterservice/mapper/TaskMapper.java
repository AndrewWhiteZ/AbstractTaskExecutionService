package ru.raperan.abstracttaskexecutorservice.masterservice.mapper;

import org.springframework.stereotype.Component;
import ru.raperan.abstracttaskexecutorservice.common.dto.TaskDto;
import ru.raperan.abstracttaskexecutorservice.masterservice.dto.TaskApiDto;
import ru.raperan.abstracttaskexecutorservice.masterservice.entity.Task;
import ru.raperan.abstracttaskexecutorservice.masterservice.web.request.CreateTaskRequest;

import java.util.stream.Collectors;

@Component
public class TaskMapper {

    private StepMapper stepMapper;

    public Task mapRequestToEntity(CreateTaskRequest request) {
        return Task.builder()
                .ttl(request.getTtl())
                .steps(request.getSteps().stream().map(
                        step -> stepMapper.mapRequestToEntity(step)
                ).collect(Collectors.toSet()))
                .build();
    }

    public TaskDto mapEntityToDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .steps(task.getSteps().stream().map(
                        step -> stepMapper.mapEntityToDto(step)
                ).collect(Collectors.toList()))
                .build();
    }

    public TaskApiDto mapEntityToApiDto(Task task) {
        return TaskApiDto.builder()
                .id(task.getId())
                .ttl(task.getTtl())
                .steps(task.getSteps().stream().map(
                        step -> stepMapper.mapEntityToApiDto(step)).collect(Collectors.toSet())
                )
                .build();
    }

}