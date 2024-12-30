package ru.raperan.abstracttaskexecutorservice.masterservice.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import ru.raperan.abstracttaskexecutorservice.masterservice.dto.TaskApiDto;
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
    @Operation(summary = "Создать новую задачу", description = "Создает задачу с описанием ttl и шагов выполнения")
    @PostMapping("/tasks")
    public ItemResponse<TaskApiDto> createTask(@Valid @RequestBody CreateTaskRequest request) {
        return new ItemResponse<>(taskService.createTask(request));
    }

    /**
     * Получить список задач
     */
    @Operation(summary = "Получить список всех созданных задач", description = "Возвращает список всех созданных ранее задач")
    @GetMapping("/tasks")
    public ListResponse<TaskApiDto> getTasks() {
        return new ListResponse<>(taskService.getTasks());
    }

    /**
     * Получить информацию о задаче
     * @param taskId UUID задачи
     */
    @Operation(summary = "Получить задачу по ID", description = "Возвращает задачу по её UUID")
    @GetMapping("/tasks/{taskId}")
    public ItemResponse<TaskApiDto> getTaskById(@PathVariable("taskId") UUID taskId) {
        return new ItemResponse<>(taskService.getTaskById(taskId));
    }

    /**
     * Выполнить задачу повторно
     * @param taskId UUID задачи
     */
    @Operation(summary = "Повторить задачу по ID", description = "Находит ранее созданую задачу по её UUID и создает её копию для выполнения")
    @PostMapping("/tasks/{taskId}")
    public ItemResponse<TaskApiDto> restartTask(@PathVariable("taskId") UUID taskId) {
        return new ItemResponse<>(taskService.restartTask(taskId));
    }

}
