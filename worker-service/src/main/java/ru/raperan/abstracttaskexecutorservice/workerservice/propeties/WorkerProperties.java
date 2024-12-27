package ru.raperan.abstracttaskexecutorservice.workerservice.propeties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("task-executor.worker")
@Getter
@Setter
public class WorkerProperties {
    private int threads;
}
