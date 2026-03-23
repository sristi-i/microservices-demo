package com.microservices.orderservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class AppConfig {

    // Day 1 -  RestTemplate bean
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    // Day 3 - WebClient bean
    // baseUrl sets the base URL so to write the path in actual calls
    // WebClient.Builder pattern allows adding filters,
    // header, timeouts cleanly before building
    @Bean
    public WebClient.Builder webClient(){
        return WebClient.builder();
    }


}