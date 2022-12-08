package com.github.dimka9910.etlservices.elasticservice.service.mq;

import com.github.dimka9910.etlservices.elasticservice.service.ElasticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@EnableRabbit
@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationEventListener {

    @Value("${coreservice.mq.queue}")
    private String queueName;
    @Value("${coreservice.mq.routingkey}")
    private String routingkey;
    @Value("${coreservice.mq.exchange}")
    private String exchange;

    private final ElasticService elasticService;


    @RabbitListener(containerFactory = "rabbitListenerContainerFactory",
            bindings = @QueueBinding(value = @Queue(name = "${coreservice.mq.queue}"),
                    key = "${coreservice.mq.routingkey}",
                    exchange = @Exchange(value = "${coreservice.mq.exchange}", type = ExchangeTypes.DIRECT)
            ))
    public void processQueue(String message) {
        try {
            log.info("Пришло сообщение: " + message);
            elasticService.getFilesAndLoadInElastic(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
