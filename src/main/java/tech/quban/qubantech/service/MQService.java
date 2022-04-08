package tech.quban.qubantech.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MQService {
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "hello")
    private void listener(String message) {
        System.out.println("Received msg: " + message);
    }

    public void sender(String msg) {
        rabbitTemplate.convertAndSend("hello", msg);
    }
}
