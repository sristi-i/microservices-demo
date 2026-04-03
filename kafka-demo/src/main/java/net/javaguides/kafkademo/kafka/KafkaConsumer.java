package net.javaguides.kafkademo.kafka;

import net.javaguides.kafkademo.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

// @KafkaListener
// marks a mthod as a Kafka message listener
// Spring automatically subscribes this method to the topic
// and calls it everyt time a new message arrives.
// No manual polling needed - Spring handles eveything
@Service
public class KafkaConsumer{

    private static final Logger LOGGER = 
            LoggerFactory.getLogger(KafkaConsumer.class);

    // topics - which topic to subscribe to
    // groupId - whihc consumer group this consumer belongs to
    // groupId in @KafkaListener - Identifies the consumer group, 
    // all consumers with same groupId share the partitions - 
    // each partition goes to only one consumer
    @KafkaListener(
        topics = AppConstants.TOPIC_NAME,
        groupId = AppConstants.GROUP_ID   
    )
    public void consume(String message){
        LOGGER.info("Message recieved from topic '{}':{}", AppConstants.TOPIC_NAME, message);
    }

}
