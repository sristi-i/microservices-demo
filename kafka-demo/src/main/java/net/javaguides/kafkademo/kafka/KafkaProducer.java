package net.javaguides.kafkademo.kafka;

import net.javaguides.kafkademo.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

// KafkaTemplate
// Spring Boot's abstraction over the Kafka Producer API
// Like JdbcTemplate for databases - handles boilerplate,
// just call send(topic, message)
@Service
public class KafkaProducer {

    private static final Logger LOGGER = 
            LoggerFactory.getLogger(KafkaProducer.class);

    // Spring Boot auto-configures KafkaTemplate based on application.properties
    // No manual bean creation needed for String messages
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message){
        LOGGER.info("Message sent to topic '{} : {}'", AppConstants.TOPIC_NAME, message);
        // kafkaTemplate.send(topicName, message)
        // KafkaTemplate.send() - publishes the message to the specified kafka topic
        // returns a completableFuture - non-blocking
        kafkaTemplate.send(AppConstants.TOPIC_NAME, message);
    }
}
