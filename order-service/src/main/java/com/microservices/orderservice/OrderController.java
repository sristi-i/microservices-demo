package com.microservices.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/orders")
public class OrderController{

    // Day 4 - RestTemplate + Eureka
    // @LaodBalanced on RestTemplate bean makes it Eureka-aware
    // "http://user-service" -> Eureka resolves to actual host:port
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/eureka/{orderId}")
    public Order getOrderViaEureka(@PathVariable int orderId) {
        User user = restTemplate.getForObject(
            "http://user-service/users/" + orderId,
            User.class
        );
        return new Order(orderId, "Tablet", user);
    }
    

    // Day 3 - WebClient via service layer
    // WebClient is non-blocking/reactive - modern replacementfor RestTemplate
    @Autowired
    private OrderService orderService;

        // Day 3 endpoint - uses WebClient
    // block() - WebClient is async by deafult - it returns Mono<User> (a future value)
    // block() waits for the response synchronously - bridges reactive to non-reactive
    // in a fully ractive app never use block() - returns Mono<Order>
    @GetMapping("/webclient/{orderId}")
    public Order getOrderViaWebClient(@PathVariable int orderId){
        return orderService.getOrderWithWebClient(orderId);
    }

    // Day 2 - Feign client
    @Autowired
    private UserClient userClient;

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable int orderId){
        
        // Day 1 - 
        // RestTemplate calls user-service running on port 8081
        // getForObject(url, responseType) - makes GET requrst and 
        // maps JSON to User object

        // RestTemplate - Spring's HTTP clinet to call otehr services REST apis from java code
        // like postman but in code

        // Day 2 - 
        // Feign client is declarative - define what to call
        // RestTemplate is imperative (write how to call)
        
        // Feign client injected just like any spring bean
        // spring creates a proxy implementation of userclient automatically
        // spring generates an HTTP GET to 
        // hettp://localhost:8081/users/{id} using Feign proxy

        User user = userClient.getUserById(1);

        return new Order(orderId, "Laptop", user);
        
    }

}