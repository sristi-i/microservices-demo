package net.javaguides.kafkademo.kafka.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import net.javaguides.kafkademo.payload.Student;

// JSON producer different from String producer
// KafkaTemplate<String, Student> instead of Kafka<String, String>
// We send a message object with headers instead of plain string
// Spring's JSONSerializer automatically converts Student to JSON bytes
@Service
public class KafkaJsonProducer {

    private static final Logger LOGGER = 
            LoggerFactory.getLogger(KafkaJsonProducer.class);

    // KafkaTemplate<String, Student> - value is now a Java object
    @Autowired
    private KafkaTemplate<String, Student> kafkaTemplate;

    public void sendMessage(Student student){
        LOGGER.info("JSON message sent: {}", student.toString());

        // Kafka builds a Kafka message with headers and payload
        // KafkaHeaders.TOPIC sets which topic to publish to.
        Message<Student> message = MessageBuilder
                .withPayload(student)
                .setHeader(KafkaHeaders.TOPIC, "javaguides_json")
                .build();
        
        kafkaTemplate.send(message);
    }

}