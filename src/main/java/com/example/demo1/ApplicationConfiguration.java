package com.example.demo1;

import com.example.demo1.repositories.OrdersRepository;
import com.example.demo1.twilioSendGrid.MailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


/**
 * Created by Daniel Bojic
 * Date: 2021-09-01
 * Time: 16:30
 * Project: Lab1
 * Copyright: MIT
 */
@Configuration
@EnableScheduling
@ConfigurationProperties("payment")
public class ApplicationConfiguration {


    @Bean
    public AuditLogger auditLogger(RabbitTemplate template, ObjectMapper objectMapper) {
        return new AuditLogger(template, objectMapper);
    }

    @Value("${}")
    String sendGridKey;

    @Bean
    public MailService mailService(){
        return new MailService(sendGridKey);
    }

    final String topicExchangeName = "payments-exchange2";

    static final String queueName = "payments2";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("#");
    }



    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory, final Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }



    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    @Value("payment.host")
    private String host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }


}
