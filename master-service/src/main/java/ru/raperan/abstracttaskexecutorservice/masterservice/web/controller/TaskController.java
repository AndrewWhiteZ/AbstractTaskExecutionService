package ru.raperan.abstracttaskexecutorservice.masterservice.web.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import ru.raperan.abstracttaskexecutorservice.masterservice.dto.TaskDto;
import ru.raperan.abstracttaskexecutorservice.masterservice.service.TaskService;
import ru.raperan.abstracttaskexecutorservice.masterservice.web.annotation.ApiV1;
import ru.raperan.abstracttaskexecutorservice.masterservice.web.request.CreateTaskRequest;
import ru.raperan.abstracttaskexecutorservice.masterservice.web.response.ItemResponse;
import ru.raperan.abstracttaskexecutorservice.masterservice.web.response.ListResponse;

import java.util.UUID;

@ApiV1
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskController {

    private TaskService taskService;

    /**
     * Создать задачу
     */
    @PostMapping("/tasks")
    public ItemResponse<TaskDto> createTask(@Valid @RequestBody CreateTaskRequest request) {
        return new ItemResponse<>(taskService.createTask(request));
    }

    /**
     * Получить список задач
     */
    @GetMapping("/tasks")
    public ListResponse<TaskDto> getTasks() {
        return new ListResponse<>(taskService.getTasks());
    }

    /**
     * Получить информацию о задаче
     * @param taskId UUID задачи
     */
    @GetMapping("/tasks/{taskId}")
    public ItemResponse<TaskDto> getTaskById(@PathVariable UUID taskId) {
        return new ItemResponse<>(taskService.getTaskById(taskId));
    }

    /**
     * Выполнить задачу повторно
     * @param taskId UUID задачи
     */
    @PostMapping("/tasks/{taskId}")
    public ItemResponse<TaskDto> restartTask(@PathVariable UUID taskId) {
        return new ItemResponse<>(taskService.restartTask(taskId));
    }

}
