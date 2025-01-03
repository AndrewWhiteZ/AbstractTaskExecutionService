package ru.raperan.abstracttaskexecutorservice.workerservice.executor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.raperan.abstracttaskexecutorservice.common.dto.PayloadDto;
import ru.raperan.abstracttaskexecutorservice.common.dto.StatusUpdateDto;
import ru.raperan.abstracttaskexecutorservice.common.dto.StepDto;
import ru.raperan.abstracttaskexecutorservice.common.dto.TaskDto;
import ru.raperan.abstracttaskexecutorservice.common.enums.Status;
import ru.raperan.abstracttaskexecutorservice.workerservice.rabbit.StatusUpdateSender;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
        log.info("Начинается выполняется задачи \"{}\"", task.getId());

        for (int i = 0; i < task.getSteps().size(); i++) {
            StringBuilder resultBuilder = new StringBuilder();
            StepDto step = task.getSteps().get(i);
            log.info("Начинается выполняется шага \"{}\" задачи \"{}\"", step.getName(), task.getId());
            //TODO добавить проверку на то не протухла ли задача

            try {
                Thread.sleep(10L);
            } catch (InterruptedException ignored) {

            }

            trySendStatusUpdate(StatusUpdateDto.updateNow(task.getId(), step.getId(), step.getName(),
                                                    Status.PROCESSING, step.getPayload().getBody() ));

            var payload = step.getPayload().getBody();
            resultBuilder.append(payload);
            try {
                Thread.sleep(payload.length() * 500L);
            } catch (InterruptedException e) {
//                resultBuilder.append(e.getMessage());

                trySendStatusUpdate(StatusUpdateDto.updateNow(task.getId(), step.getId(), step.getName(),
                        Status.ERROR, resultBuilder.toString()));
                return;
            }

            resultBuilder.append(String.format("_%s", step.getName()));
            trySendStatusUpdate(StatusUpdateDto.updateNow(task.getId(), step.getId(), step.getName(),
                                                    Status.FINISHED, resultBuilder.toString()));
            if (  i+1 < task.getSteps().size() ) {
                task.getSteps().get(i+1).setPayload(PayloadDto.builder()
                                .body(resultBuilder.toString())
                        .build());

            }

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
