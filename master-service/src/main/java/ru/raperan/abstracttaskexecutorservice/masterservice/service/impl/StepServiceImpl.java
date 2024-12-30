package ru.raperan.abstracttaskexecutorservice.masterservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.raperan.abstracttaskexecutorservice.common.dto.StatusUpdateDto;
import ru.raperan.abstracttaskexecutorservice.masterservice.repository.StepRepository;
import ru.raperan.abstracttaskexecutorservice.masterservice.service.StepService;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class StepServiceImpl implements StepService {

    private StepRepository stepRepository;

    @Override
    public void updateStatus(StatusUpdateDto dto) {
        stepRepository.updateStepStatus(dto.getStatus(), dto.getMessage(), dto.getTaskId(), dto.getStepId());
    }

}
