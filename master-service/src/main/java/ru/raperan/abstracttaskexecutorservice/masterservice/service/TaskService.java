package ru.raperan.abstracttaskexecutorservice.masterservice.service;

import java.util.List;
import java.util.UUID;

import ru.raperan.abstracttaskexecutorservice.masterservice.dto.TaskApiDto;
import ru.raperan.abstracttaskexecutorservice.masterservice.web.request.CreateTaskRequest;

public interface TaskService {
    TaskApiDto createTask(CreateTaskRequest request);
    List<TaskApiDto> getTasks();
    TaskApiDto getTaskById(UUID id);
    TaskApiDto restartTask(UUID id);
}
