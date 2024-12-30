package ru.raperan.abstracttaskexecutorservice.masterservice.web.request;

import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@AllArgsConstructor
public class CreateTaskRequest {

    @NotNull
    private String payload;

    @NotNull
    private Long ttl;

    @NotNull
    private List<String> steps;

}
