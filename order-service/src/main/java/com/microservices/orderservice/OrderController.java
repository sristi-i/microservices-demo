package com.microservices.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
public class OrderController{

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable int orderId){
        
        // RestTemplate calls user-service running on port 8081
        // getForObject(url, responseType) - makes GET requrst and 
        // maps JSON to User object

        // RestTemplate - Spring's HTTP clinet to call otehr services REST apis from java code
        // like postman but in code

        User user = restTemplate.getForObject(
            "http://localhost:8081/users/1",
            User.class
        );
        return new Order(orderId, "Laptop", user);
    }

}