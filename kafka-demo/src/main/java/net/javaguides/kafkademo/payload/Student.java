package net.javaguides.kafkademo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// seperate model class for JSON messages
// Kafka transfers bytes. For JSON, we need a Java class
// that Jackson can serialize/desearlaize
// Producer converts Student -> JSON bytes -> Kafka topic
// Consumer reads bytes -> JSON -> Student object
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private int id;
    private String firstName;
    private String lasName;
    private String email;
}