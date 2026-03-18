package com.microservices.orderservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient - name is logical name, url is where uerser-service is running
// Its a decalrative http client - you define an interface and spring generates
// the actual HTTP call at runtime, no manual RestTemplate code needed
@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface UserClient {

    // this maps exactly to user-service's GET /user/{id} endpoint
    // Feign client reads @GetMapping and @PAthVaariable same as a controller - ut makes HTTP call instead
    @GetMapping("/users/{id}")
    User getUserById(@PathVariable int id);
}