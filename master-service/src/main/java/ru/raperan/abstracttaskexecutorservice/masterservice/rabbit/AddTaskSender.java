package ru.raperan.abstracttaskexecutorservice.masterservice.rabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import ru.raperan.abstracttaskexecutorservice.common.dto.TaskDto;
import ru.raperan.abstracttaskexecutorservice.common.rabbit.RabbitmqConst;

@Component
@RequiredArgsConstructor
public class AddTaskSender {

    private final RabbitTemplate rabbitTemplate;

    public void send(TaskDto message) {
        rabbitTemplate.convertAndSend(
                RabbitmqConst.ADD_TASK_DIRECT_EXCHANGE_NAME,
                RabbitmqConst.ADD_TASK_QUEUE_NAME,
                message
        );
    }

}
