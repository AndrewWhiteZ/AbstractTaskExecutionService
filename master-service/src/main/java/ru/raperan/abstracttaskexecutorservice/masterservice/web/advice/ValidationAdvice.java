package ru.raperan.abstracttaskexecutorservice.masterservice.web.advice;

import jakarta.validation.ConstraintViolationException;
import lombok.var;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import ru.raperan.abstracttaskexecutorservice.masterservice.constant.StatusCode;
import ru.raperan.abstracttaskexecutorservice.masterservice.web.annotation.ApiAdvice;
import ru.raperan.abstracttaskexecutorservice.masterservice.web.response.ValidationErrorResponse;

import java.util.HashSet;
import java.util.stream.Collectors;

@ApiAdvice
public class ValidationAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ValidationErrorResponse> notValid(MethodArgumentNotValidException e) {
        var fields = e.getFieldErrors().stream().map(FieldError::getField).collect(Collectors.toSet());
        return ResponseEntity
                .status(StatusCode.VALIDATION_ERROR.getHttpCode())
                .body(new ValidationErrorResponse(fields));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ValidationErrorResponse> constraintViolation(ConstraintViolationException e) {
        var fields = e.getConstraintViolations().stream().map((violation) -> {
            var path = violation.getPropertyPath().toString();
            return path.substring(path.lastIndexOf('.') + 1);
        }).collect(Collectors.toSet());
        return ResponseEntity
                .status(StatusCode.VALIDATION_ERROR.getHttpCode())
                .body(new ValidationErrorResponse(fields));
    }

    @ExceptionHandler({MissingServletRequestPartException.class})
    ResponseEntity<ValidationErrorResponse> requestPartMissing(MissingServletRequestPartException e) {
        var fields = new HashSet<String>();
        fields.add(e.getRequestPartName());
        return ResponseEntity
                .status(StatusCode.VALIDATION_ERROR.getHttpCode())
                .body(new ValidationErrorResponse(fields));
    }

}
