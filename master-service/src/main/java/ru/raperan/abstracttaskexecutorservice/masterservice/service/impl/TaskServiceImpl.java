package ru.raperan.abstracttaskexecutorservice.masterservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.raperan.abstracttaskexecutorservice.masterservice.dto.TaskDto;
import ru.raperan.abstracttaskexecutorservice.masterservice.service.TaskService;
import ru.raperan.abstracttaskexecutorservice.masterservice.web.request.CreateTaskRequest;

import java.util.List;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    @Override
    public TaskDto createTask(CreateTaskRequest request) {
        return null;
    }

    @Override
    public List<TaskDto> getTasks() {
        return null;
    }

    @Override
    public TaskDto getTaskById(UUID id) {
        return null;
    }

    @Override
    public TaskDto restartTask(UUID id) {
        return null;
    }

}
