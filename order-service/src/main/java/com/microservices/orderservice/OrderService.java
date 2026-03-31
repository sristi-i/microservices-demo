package com.microservices.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

// circuit breaker in service layer
// business logic and resilience belong in service layer
// controller only handles http - seperation of concerns
// business logic goes in @Service, not directly in controller
@Service
public class OrderService {

    @Autowired
    private RestTemplate restTemplate;

    // Day 8 - Resilience4j Circuit Breaker
    //  name must match key in application.properties
    //  @CircuitBreaker - Resilience4j annotation that wraps 
    // method with circuit breaker protection
    // when restaurant-service fails repeatedly -> circuit opens -> fallback called
    @CircuitBreaker(name="restaurantService", fallbackMethod = "restaurantFallback")
    public Order getOrderWithRestaurant(int orderId){
        //  calls restaurant-service - if its DOWN - circuit breaker triggers fallback
        Restaurant restaurant = restTemplate.getForObject(
            "http://restaurant-service/restaurants/" + orderId,
            Restaurant.class
        );

        return new Order(orderId, restaurant.getName(), null);
    }

    // fallback method - same signature as original + Throwable parameter
    // Resilience4j fallback must have extra Throwable parameter at the end
    // Hystrix only needed same parameter as original
    public Order restaurantFallback(int orderId, Throwable throwable){
        // called when circuit open or expception thrown
        // returns meaningful default instead if crashing
        System.out.println("Fallback trigerred! Cause: " + throwable.getMessage());
        return new Order(orderId, "Default restaurant - service down!!!", null);    
    }

    // WebClinet - non-blocking HTTP client from Spring WebFlux
    // supports sync, async and streaming
    @Autowired
    private WebClient.Builder webClientBuilder;

    // Day 3 - WebClient
    public Order getOrderWithWebClient(int orderId){
        User user = webClientBuilder
                    .baseUrl("http://user-service")
                    .build()
                    .get()
                    .uri("/users/{id}", orderId)
                    .retrieve()
                    .bodyToMono(User.class)
                    .block();

        return new Order(orderId, "Phone", user);
    }
}