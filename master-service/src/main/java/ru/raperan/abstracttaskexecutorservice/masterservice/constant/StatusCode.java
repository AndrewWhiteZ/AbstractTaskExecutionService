package ru.raperan.abstracttaskexecutorservice.masterservice.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum StatusCode {

    UNKNOWN(-1, 500, "Неизвестная ошибка"),
    OK(0, 200, null),
    VALIDATION_ERROR(1, 400, "Ошибка валидации"),

    /**
     * Статусы задач
     */
    TASK_NOT_FOUND(100, 400, "Задача не найдена");

    int code;
    int httpCode;
    String message;

}
