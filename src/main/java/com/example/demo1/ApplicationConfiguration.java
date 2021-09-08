package com.example.demo1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Daniel Bojic
 * Date: 2021-09-01
 * Time: 16:30
 * Project: Lab1
 * Copyright: MIT
 */
@Configuration
@ConfigurationProperties("payment")
public class ApplicationConfiguration {


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
