package ru.raperan.abstracttaskexecutorservice.masterservice.exception;

import ru.raperan.abstracttaskexecutorservice.masterservice.constant.StatusCode;

public class TaskNotFoundException extends BaseException {
    public TaskNotFoundException() {
        super(StatusCode.TASK_NOT_FOUND);
    }
}
