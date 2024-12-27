package ru.raperan.abstracttaskexecutorservice.workerservice.rabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import ru.raperan.abstracttaskexecutorservice.common.dto.StatusUpdateDto;
import ru.raperan.abstracttaskexecutorservice.common.rabbit.RabbitmqConst;

@Component
@RequiredArgsConstructor
public class StatusUpdateSender {

    private final RabbitTemplate rabbitTemplate;

    public void send(StatusUpdateDto message) {
        rabbitTemplate.convertAndSend(RabbitmqConst.UPDATE_STATUS_DIRECT_EXCHANGE_NAME,
                                      RabbitmqConst.UPDATE_STATUS_QUEUE_NAME,
                                      message);
    }

}
