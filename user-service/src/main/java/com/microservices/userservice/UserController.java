package com.microservices.userservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController{

    // @Value reads from config server at startup
    // how does @Value get its value from config server?
    // on startup, spring boot fetches all properties from config server?
    // and makes then available via @Value just like application.properties
    @Value("${user.greeting:Default greeting}")
    private String greeting;

    @Value("${build.version:0.0}")
    private String buildversion;

    // new end point to test config server si working
    // hit this endpoint - if it returns value from github, config server works!
    @GetMapping("/config")
    public String getConfig(){
        return "Greeting: " + greeting + " | Version: " + buildversion;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return new User(id, "Sristi", "sristi@email.com");
    }
}