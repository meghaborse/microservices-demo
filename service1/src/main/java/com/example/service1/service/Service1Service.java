package com.example.service1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Service1Service {
    private static final Logger logger = LoggerFactory.getLogger(Service1Service.class);
    private final RestTemplate restTemplate;
    public Service1Service() {
        this.restTemplate = new RestTemplate();
    }
    public String orchestrate(Map<String, String> payload) throws Exception {

        logger.info("Orchestrating request service1->service2->service3");

        // Call Service2
        String service2Response = restTemplate.getForObject("http://localhost:8081/service2/hello", String.class);

        // Call Service3
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);
        String service3Response = restTemplate.postForObject("http://localhost:8082/service3/concat", request, String.class);
        return service2Response + " " + service3Response;
    }
}
