package ru.raperan.abstracttaskexecutorservice.masterservice.web.request;

import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskRequest {

    @NotNull
    private String payload;

    @NotNull
    private Long ttl;

    @NotNull
    private List<String> steps;

}
