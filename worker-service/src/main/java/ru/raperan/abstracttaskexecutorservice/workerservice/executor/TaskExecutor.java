package ru.raperan.abstracttaskexecutorservice.workerservice.executor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.raperan.abstracttaskexecutorservice.common.dto.StatusUpdateDto;
import ru.raperan.abstracttaskexecutorservice.common.dto.StepDto;
import ru.raperan.abstracttaskexecutorservice.common.dto.TaskDto;
import ru.raperan.abstracttaskexecutorservice.common.enums.Status;
import ru.raperan.abstracttaskexecutorservice.workerservice.rabbit.StatusUpdateSender;

import java.util.concurrent.ScheduledExecutorService;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskExecutor {

    private static final int MESSAGE_SEND_TRIES = 5;

    private static final int MESSAGE_SEND_DELAY = 1000;

    private final ScheduledExecutorService executorService;

    private final StatusUpdateSender statusUpdateSender;

    public void executeTask(TaskDto task) {
        executorService.execute(() -> executeTaskInternal(task));
    }

    private void executeTaskInternal(TaskDto task) {
        StringBuilder resultBuilder = new StringBuilder();
        log.info("Начинается выполняется задачи \"{}\"", task.getId());

        for (StepDto step : task.getSteps()) {
            log.info("Начинается выполняется шага \"{}\" задачи \"{}\"", step.getName(), task.getId());

            trySendStatusUpdate(new StatusUpdateDto(task.getId(), step.getId(),
                                                    Status.PROCESSING, null));

            var payload = step.getPayload().getBody();
            try {
                Thread.sleep(payload.length() * 1000L);
            } catch (InterruptedException e) {
                return;
            }
            resultBuilder.append(payload);

            trySendStatusUpdate(new StatusUpdateDto(task.getId(), step.getId(),
                                                    Status.FINISHED, resultBuilder.toString()));

        }

        log.info("Закончено выполнение задачи \"{}\"", task.getId());
    }

    private void trySendStatusUpdate(StatusUpdateDto statusUpdate) {
        for (int i = 0; i < MESSAGE_SEND_TRIES; i++) {
            try {
                statusUpdateSender.send(statusUpdate);
                break;
            } catch (Exception e) {
                log.error("Не удалось отправить сообщение {}, попытка {}/{}", statusUpdate, i + 1, MESSAGE_SEND_TRIES);
                try {
                    Thread.sleep(MESSAGE_SEND_DELAY);
                } catch (InterruptedException ie) {
                    return;
                }
            }
        }
    }

}
