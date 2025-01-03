package ru.raperan.abstracttaskexecutorservice.masterservice.mapper;

import org.springframework.stereotype.Component;
import ru.raperan.abstracttaskexecutorservice.common.dto.PayloadDto;
import ru.raperan.abstracttaskexecutorservice.common.dto.StepDto;
import ru.raperan.abstracttaskexecutorservice.common.enums.Status;
import ru.raperan.abstracttaskexecutorservice.masterservice.dto.StepApiDto;
import ru.raperan.abstracttaskexecutorservice.masterservice.entity.Step;
import ru.raperan.abstracttaskexecutorservice.masterservice.entity.StepId;

import java.time.LocalDateTime;

@Component
public class StepMapper {

    public Step mapRequestToEntity(String request) {
        return Step.builder()
                .name(request)
                .startTime(LocalDateTime.now())
                .stepId(StepId.builder()
                        .status(Status.PENDING)
                        .build())
                .build();
    }

    public StepDto mapEntityToDto(Step step) {
        return StepDto.builder()
                .id(step.getStepId().getId())
                .name(step.getName())
                .payload(PayloadDto.builder().body(step.getPayloadAsString()).build())
                .build();
    }

    public StepApiDto mapEntityToApiDto(Step step) {
        return StepApiDto.builder()
                .name(step.getName())
                .status(String.valueOf(step.getStepId().getStatus()))
                .payload(step.getPayloadAsString())
                .startTime(step.getStartTime())
                .build();

    }

}
