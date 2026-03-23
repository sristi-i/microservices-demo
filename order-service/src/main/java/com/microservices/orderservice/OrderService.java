package com.microservices.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

// business logic goes in @Service, not directly in controller
@Service
public class OrderService {

    // WebClinet - non-blocking HTTP client from Spring WebFlux
    // supports sync, async and streaming
    @Autowired
    private WebClient.Builder webClientBuilder;


    public Order getOrderWithWebClient(int orderId){
        User user = webClientBuilder
                    .baseUrl("http://localhost:8081")
                    .build()
                    .get()
                    .uri("/users/{id}", orderId)
                    .retrieve()
                    .bodyToMono(User.class)
                    .block();

        return new Order(orderId, "Phone", user);
    }
}