package com.github.dimka9910.etlservices.coreservice.service.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class CoreMqService {

    @Value("${coreservice.mq.routingkey}")
    private String routingkey;
    @Value("${coreservice.mq.exchange}")
    private String exchange;

    private final RabbitTemplate rabbitNotificationMqClient;

    public void sendMessage(String path){
        rabbitNotificationMqClient.convertAndSend(exchange, routingkey, path);
    }

}

