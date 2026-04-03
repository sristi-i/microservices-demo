package net.javaguides.kafkademo.kafka.json;

import net.javaguides.kafkademo.payload.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaJsonConsumer {

    private static Logger LOGGER = 
            LoggerFactory.getLogger(KafkaJsonConsumer.class);

    // JSON cosumer - Spring's JsonDeserializer automatically converts
    // JSON bytes back to Student object.
    // The methid parameters type tells Spring what class to deserailaize to.
    @KafkaListener(
        topics = "javaguides_json",
        groupId = "myGroup"
    )
    public void consume(Student student){
        LOGGER.info("JSON message recieved: {}", student.toString());
    }

}
