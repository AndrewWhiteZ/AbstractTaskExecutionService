package ru.raperan.abstracttaskexecutorservice.workerservice.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.raperan.abstracttaskexecutorservice.common.dto.TaskDto;
import ru.raperan.abstracttaskexecutorservice.common.rabbit.RabbitmqConst;
import ru.raperan.abstracttaskexecutorservice.workerservice.executor.TaskExecutor;

@Component
@RequiredArgsConstructor
@Slf4j
public class AddTaskMessageReceiver {

    private final TaskExecutor taskExecutor;

    @RabbitListener(queues = RabbitmqConst.ADD_TASK_QUEUE_NAME)
    public void receive(@Payload TaskDto task) {
        log.info("Получено сообщение: {}", task);
        taskExecutor.executeTask(task);
    }

}
