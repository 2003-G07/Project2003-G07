package com.example.demo1.messaging;

import com.example.demo1.models.OrderDetails;
import com.example.demo1.models.Orders;
import com.example.demo1.repositories.OrderDetailsRepository;
import com.example.demo1.repositories.OrdersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Daniel Bojic
 * Date: 2021-09-13
 * Time: 12:54
 * Project: gr7java
 * Copyright: MIT
 */
@Component
public class Receiver {

    private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private OrdersRepository ordersRepository;

    Orders newOrder = new Orders();

    @RabbitListener(queues = "payments2")
    public void recieveMessage(PaymentMessageDto paymentMessageDto){
        LOG.info("MEDD KOMISH FRAMISH");
        System.out.println("MEDDELANDET KOM FRAM!!!!!");
        System.out.println(paymentMessageDto.getStatus());

        if (paymentMessageDto.getStatus().equals("CREATED")){
            System.out.println("ÄNDRAR TILL CREATED");
          newOrder = ordersRepository.findById(Long.parseLong(paymentMessageDto.getReference())).get();
          newOrder.setStatus(2);
          ordersRepository.save(newOrder);
        }else if (paymentMessageDto.getStatus().equals("PAID")){
            System.out.println("ÄNDRAR TILL PAID");
            newOrder = ordersRepository.findById(Long.parseLong(paymentMessageDto.getReference())).get();
            newOrder.setStatus(3);
            ordersRepository.save(newOrder);
        }
        
    }

}