package ru.raperan.abstracttaskexecutorservice.masterservice.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.raperan.abstracttaskexecutorservice.common.dto.StatusUpdateDto;
import ru.raperan.abstracttaskexecutorservice.common.rabbit.RabbitmqConst;
import ru.raperan.abstracttaskexecutorservice.masterservice.service.StepService;

@Component
@RequiredArgsConstructor
@Slf4j
public class StatusUpdateMessageReceiver {

    private StepService stepService;

    @RabbitListener(queues = RabbitmqConst.ADD_TASK_QUEUE_NAME)
    public void receive(@Payload StatusUpdateDto dto) {
        log.info("Получено сообщение: {}", dto);
        stepService.updateStatus(dto);
    }

}
