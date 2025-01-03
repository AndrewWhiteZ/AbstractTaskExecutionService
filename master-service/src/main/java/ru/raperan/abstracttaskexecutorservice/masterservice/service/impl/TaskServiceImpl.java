package ru.raperan.abstracttaskexecutorservice.masterservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.raperan.abstracttaskexecutorservice.masterservice.dto.TaskApiDto;
import ru.raperan.abstracttaskexecutorservice.masterservice.entity.Step;
import ru.raperan.abstracttaskexecutorservice.masterservice.entity.Task;
import ru.raperan.abstracttaskexecutorservice.masterservice.exception.TaskNotFoundException;
import ru.raperan.abstracttaskexecutorservice.masterservice.mapper.TaskMapper;
import ru.raperan.abstracttaskexecutorservice.masterservice.rabbit.AddTaskSender;
import ru.raperan.abstracttaskexecutorservice.masterservice.repository.TaskRepository;
import ru.raperan.abstracttaskexecutorservice.masterservice.service.TaskService;
import ru.raperan.abstracttaskexecutorservice.masterservice.web.request.CreateTaskRequest;

import java.util.*;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private TaskMapper taskMapper;

    private AddTaskSender addTaskSender;

    @Override
    public TaskApiDto createTask(CreateTaskRequest request) {
        Task task = taskMapper.mapRequestToEntity(request);
        task.addSteps(task.getSteps());
        Task savedTask = taskRepository.save(task);
        addTaskSender.send(taskMapper.mapEntityToDto(savedTask));
        return taskMapper.mapEntityToApiDto(savedTask);
    }

    @Override
    public List<TaskApiDto> getTasks() {
        List<Task> taskList = taskRepository.findAll();
        return taskList.stream().map(taskMapper::mapEntityToApiDto).collect(Collectors.toList());
    }

    @Override
    public TaskApiDto getTaskById(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        task.getSteps()
//                .stream().sorted(Comparator.comparing( s -> s.getStepId().getStatus() )).toList().reversed()
                .sort(Comparator.comparing(Step::getStartTime));
        return taskMapper.mapEntityToApiDto(task);
    }

    @Override
    public TaskApiDto restartTask(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        Task newTask = Task.builder()
                .ttl(task.getTtl())
                .steps(copySteps(task.getSteps()))
                .parentTask(task)
                .build();
        newTask.addSteps(newTask.getSteps());
        Task savedTask = taskRepository.save(newTask);
        addTaskSender.send(taskMapper.mapEntityToDto(savedTask));
        return taskMapper.mapEntityToApiDto(savedTask);
    }

    private List<Step> copySteps(Collection<Step> steps) {
        return steps.stream().map(step -> Step.builder().name(step.getName()).payload(step.getPayload()).build()).collect(Collectors.toList());
    }

}
