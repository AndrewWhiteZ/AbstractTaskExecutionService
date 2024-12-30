package ru.raperan.abstracttaskexecutorservice.masterservice.service;

import ru.raperan.abstracttaskexecutorservice.common.dto.StatusUpdateDto;

public interface StepService {
    void updateStatus(StatusUpdateDto dto);
}
