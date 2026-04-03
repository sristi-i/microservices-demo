package net.javaguides.kafkademo.controller;

import net.javaguides.kafkademo.payload.Student;
import net.javaguides.kafkademo.kafka.KafkaProducer;
import net.javaguides.kafkademo.kafka.json.KafkaJsonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



// trigger the producer via HTTP request
// In production, the producer would be triggered by business events,
// not HTTP calls

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private KafkaJsonProducer kafkaJsonProducer;
    
    // send a String esage to Kafka
    // Test: GET /api/v1/publish?message=HElloKafka
    @GetMapping("/publish")
    public ResponseEntity<String> publishMessage(
        @RequestParam("message") String message) {
            kafkaProducer.sendMessage(message);
            return ResponseEntity.ok("Message sent to Kafka topic: " + message);
    }

    // send a JSON (Studetn obkject) mesage to Kafka
    // Test: POST /api/v1/publish with JSON body
    @PostMapping("/publish")
    public ResponseEntity<String> publishJsonMessage(
        @RequestBody Student student) {
        kafkaJsonProducer.sendMessage(student);
        return ResponseEntity.ok("Message sent to Kafka topic: " + student.toString());
    }
    

}
