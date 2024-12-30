package ru.raperan.abstracttaskexecutorservice.workerservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.raperan.abstracttaskexecutorservice.workerservice.propeties.WorkerProperties;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Configuration
@RequiredArgsConstructor
public class ThreadExecutorConfig {
    private final WorkerProperties workerProperties;

    @Bean
    public ScheduledExecutorService executorService() {
        int numThreads = workerProperties.getThreads();
        if (numThreads == 0) {
            numThreads = Runtime.getRuntime().availableProcessors();
        }

        var executor = new ScheduledThreadPoolExecutor(numThreads);
        executor.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
        return executor;
    }
}
