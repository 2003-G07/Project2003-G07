package com.example.demo1.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.service.Header;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel Bojic
 * Date: 2021-09-07
 * Time: 16:55
 * Project: gr7java
 * Copyright: MIT
 */
public class Serialize implements Serializable {

    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    PaymentDto paymentDto = new PaymentDto();

    HttpHeaders headers = new HttpHeaders();




    public void sendDTO(String url) throws IOException {

       headers.setContentType(MediaType.APPLICATION_JSON);
       PaymentDto paymentDto = new PaymentDto();
       headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

       String urlPost = "http://"+ url+"/payment";



       Map<String, Object> map = new HashMap<>();
       map.put("reference", "12");
       map.put("amount", "1");

       HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

       ResponseEntity<PaymentDto> response = this.restTemplate.postForEntity(urlPost,entity,PaymentDto.class);


    }

    public void receiveDTO(String url){
        String urlPost = "http://"+ url+"/payment";

        restTemplate.getForEntity("http://"+url, PaymentDto.class).getBody().getAmount();
    }
   // return Boolean.parseBoolean(restTemplate.getForEntity("http://"+url+"/risk/"+ userId, Pass.class).getBody().getPass());

}
