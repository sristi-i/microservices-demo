package com.microservices.restaurantservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    // GET single restaurant by id
    // this will be called by oder-sevice via Feign
    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable int id) {
        return new Restaurant(id, "Pizza Palace", "Mumbai", "Italian");
    }

    // GET all restaurants
    // accessible via Gateway at /restaurants
    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return List.of(
            new Restaurant(1, "Pizza Palace", "Mumbai", "Italian"),
            new Restaurant(2, "Curry House", "Delhi", "Indian"),
            new Restaurant(3, "Dragon Wok", "Bangalore", "Chinese")
        );
    }
    
    
    
}
