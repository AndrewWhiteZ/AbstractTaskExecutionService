package ru.raperan.abstracttaskexecutorservice.masterservice.exception;

import lombok.Getter;
import ru.raperan.abstracttaskexecutorservice.masterservice.constant.StatusCode;

@Getter
public abstract class BaseException extends RuntimeException {
    private final StatusCode status;

    public BaseException(StatusCode status) {
        this.status = status;
    }

    public BaseException(StatusCode status, String message) {
        super(message);
        this.status = status;
    }

    public BaseException(StatusCode status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public BaseException(StatusCode status, Throwable cause) {
        super(cause);
        this.status = status;
    }

    public BaseException(StatusCode status,
                         String message,
                         Throwable cause,
                         boolean enableSuppression,
                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }
}
