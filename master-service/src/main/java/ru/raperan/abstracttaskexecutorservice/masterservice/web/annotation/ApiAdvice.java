package ru.raperan.abstracttaskexecutorservice.masterservice.web.annotation;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.annotation.*;

@Documented
@RestController
@Retention(RetentionPolicy.RUNTIME)
@RestControllerAdvice
public @interface ApiAdvice {
}
