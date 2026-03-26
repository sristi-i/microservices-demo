package com.microservices.restaurantservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant{

    private int id;
    private String name;
    private String location;
    private String cusine;
}