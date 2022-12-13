package com.github.dimka9910.etlservices.elasticservice.tranformer.module.app.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqServiceConfiguration {

    @Value("${coreservice.mq.host:#{null}}")
    private String host;
    @Value("${coreservice.mq.port:#{null}}")
    private Integer port;
    @Value("${coreservice.mq.username:#{null}}")
    private String username;
    @Value("${coreservice.mq.password:#{null}}")
    private String password;

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean(name = "coreConnectionFactory")
    @ConditionalOnMissingBean(ConnectionFactory.class)
    public ConnectionFactory coreConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean("rabbitNotificationMqClient")
    public RabbitTemplate rabbitNotificationMqClient(@Qualifier("coreConnectionFactory") ConnectionFactory coreConnectionFactory, MessageConverter jsonMessageConverter) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(coreConnectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        return rabbitTemplate;
    }
}
