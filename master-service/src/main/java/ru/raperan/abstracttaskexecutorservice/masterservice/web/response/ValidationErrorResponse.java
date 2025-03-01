package ru.raperan.abstracttaskexecutorservice.masterservice.web.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.raperan.abstracttaskexecutorservice.masterservice.constant.StatusCode;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValidationErrorResponse extends ApiResponse {

    public static final StatusCode STATUS = StatusCode.VALIDATION_ERROR;
    Set<String> invalidFields;

    public ValidationErrorResponse(Set<String> invalidFields) {
        super.setStatus(STATUS.getCode());
        super.setMessage(STATUS.getMessage());
        this.invalidFields = invalidFields;
    }

}
