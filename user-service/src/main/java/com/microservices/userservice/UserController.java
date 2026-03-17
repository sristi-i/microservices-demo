package com.microservices.userservice;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController{
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return new User(id, "Sristi", "sristi@email.com");
    }
}