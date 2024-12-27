package ru.raperan.abstracttaskexecutorservice.common.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import static ru.raperan.abstracttaskexecutorservice.common.rabbit.RabbitmqConst.*;

@AutoConfiguration
public class RabbitmqAutoConfig {

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue addTaskQueue() {
        return QueueBuilder
                .durable(ADD_TASK_QUEUE_NAME)
                .build();
    }

    @Bean
    public DirectExchange addTaskDirectExchange() {
        return ExchangeBuilder
                .directExchange(ADD_TASK_DIRECT_EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    @Bean
    public Binding addTaskBinding() {
        return BindingBuilder.bind(addTaskQueue())
                             .to(addTaskDirectExchange())
                             .withQueueName();
    }

    @Bean
    public Queue updateStatusQueue() {
        return QueueBuilder
                .durable(UPDATE_STATUS_QUEUE_NAME)
                .build();
    }

    @Bean
    public DirectExchange updateStatusDirectExchange() {
        return ExchangeBuilder
                .directExchange(UPDATE_STATUS_DIRECT_EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    @Bean
    public Binding updateStatusBinding() {
        return BindingBuilder.bind(updateStatusQueue())
                             .to(updateStatusDirectExchange())
                             .withQueueName();
    }
}
