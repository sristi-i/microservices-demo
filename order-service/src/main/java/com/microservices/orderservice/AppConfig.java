package com.microservices.orderservice;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    // @LoadBalanced - RestTemplate now uses Eureka to resolve service names
    // It intercepts HTTP calls and replaces "user-service" with actual host:port from Eureka

    // Day 3 - WebClient bean
    // baseUrl sets the base URL so to write the path in actual calls
    // WebClient.Builder pattern allows adding filters,
    // header, timeouts cleanly before building
    @Bean
    @LoadBalanced // tells spring to use Eureka to resolve "http://user-service" to actual host:port
    public WebClient.Builder webClient(){
        return WebClient.builder();
    }

    // Day 1 -  RestTemplate bean
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}