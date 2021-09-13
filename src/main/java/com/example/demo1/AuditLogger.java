package com.example.demo1;

import com.example.demo1.messaging.PaymentMessageDto;
import com.example.demo1.util.PaymentDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


/**
 * Created by Daniel Bojic
 * Date: 2021-09-09
 * Time: 19:08
 * Project: gr7java
 * Copyright: MIT
 */
public class AuditLogger {


    private final static Logger LOG = LoggerFactory.getLogger(AuditLogger.class);

    private final RabbitTemplate template;
    private final ObjectMapper objectMapper;

    final String topicExchangeName = "payments-exchange";

    public AuditLogger(RabbitTemplate template, ObjectMapper objectMapper) {

        this.template = template;
        this.objectMapper = objectMapper;
    }


    public void notify(PaymentDto paymentDto) {

        try {
           String x = objectMapper.writeValueAsString(new PaymentMessageDto("12", "12", "CREATED"));

           PaymentMessageDto paymentMessageDto = new PaymentMessageDto(paymentDto.getReference(), "0", "CREATED");

        LOG.info("Sending..");
        template.convertAndSend(topicExchangeName, paymentDto.getReference(), paymentMessageDto);
    } catch (JsonProcessingException e) {
        e.printStackTrace();
    }

        System.out.println("AUDIT FUNKADE");

    }



}
