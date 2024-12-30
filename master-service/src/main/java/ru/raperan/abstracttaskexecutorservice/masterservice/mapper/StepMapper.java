package ru.raperan.abstracttaskexecutorservice.masterservice.mapper;

import org.springframework.stereotype.Component;
import ru.raperan.abstracttaskexecutorservice.common.dto.PayloadDto;
import ru.raperan.abstracttaskexecutorservice.common.dto.StepDto;
import ru.raperan.abstracttaskexecutorservice.masterservice.dto.StepApiDto;
import ru.raperan.abstracttaskexecutorservice.masterservice.entity.Step;

@Component
public class StepMapper {

    public Step mapRequestToEntity(String request) {
        return Step.builder()
                .name(request)
                .build();
    }

    public StepDto mapEntityToDto(Step step) {
        return StepDto.builder()
                .id(step.getId())
                .name(step.getName())
                .payload(PayloadDto.builder().body(step.getPayload().toString()).build())
                .build();
    }

    public StepApiDto mapEntityToApiDto(Step step) {
        return StepApiDto.builder()
                .name(step.getName())
                .status(String.valueOf(step.getStatus()))
                .payload(step.getPayload().toString())
                .result(step.getResult().toString())
                .startTime(step.getStartTime())
                .build();

    }

}
