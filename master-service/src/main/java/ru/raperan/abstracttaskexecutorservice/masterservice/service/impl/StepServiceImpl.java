package ru.raperan.abstracttaskexecutorservice.masterservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.raperan.abstracttaskexecutorservice.common.dto.StatusUpdateDto;
import ru.raperan.abstracttaskexecutorservice.common.enums.Status;
import ru.raperan.abstracttaskexecutorservice.masterservice.entity.Payload;
import ru.raperan.abstracttaskexecutorservice.masterservice.entity.Step;
import ru.raperan.abstracttaskexecutorservice.masterservice.entity.StepId;
import ru.raperan.abstracttaskexecutorservice.masterservice.repository.StepRepository;
import ru.raperan.abstracttaskexecutorservice.masterservice.repository.TaskRepository;
import ru.raperan.abstracttaskexecutorservice.masterservice.service.StepService;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class StepServiceImpl implements StepService {

    private final StepRepository stepRepository;
    private final TaskRepository taskRepository;


    @Override
    public void updateStatus(StatusUpdateDto dto) {
//        stepRepository.updateStepStatus(dto.getStatus(), dto.getMessage(), dto.getTaskId(), dto.getStepId());



        stepRepository.save(Step.builder()
                        .stepId(
                                StepId.builder()
                                        .id(dto.getStepId())
                                        .status(Status.valueOf(dto.getStatus()))
                                        .build()
                                )
                        .name(dto.getName())
                        .startTime(dto.getStartTime())
                        .payload(Payload.builder()
                                .body(dto.getMessage())
                                .build())
                        .task(taskRepository.findById(dto.getTaskId()).orElseThrow())
                .build());
    }

}
