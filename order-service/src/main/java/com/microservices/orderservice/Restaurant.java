package com.microservices.orderservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// local copy - loose coupling, order-service doesnt depend on restaurant-service code
// why copy model instead of sharing - microservices must to independent 
// nno shared code between servoces
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant{

    private int id;
    private String name;
    private String location;
    private String cusine;
}