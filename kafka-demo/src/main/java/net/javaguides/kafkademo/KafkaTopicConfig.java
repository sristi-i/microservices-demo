package net.javaguides.kafkademo;

import net.javaguides.kafkademo.utils.AppConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

// create a kafka topic in Spring boot
// Declare a NewTopic bean using TopicBuilder
// Spring Boot auto-detects it and creates the topic on startup
// if topic already exists, this bean is simply ignored
@Configuration
public class KafkaTopicConfig {
    
    @Bean
    public NewTopic javaguidesTopic(){
        return TopicBuilder
                .name(AppConstants.TOPIC_NAME)
                .build();
    }

    // JSON topic - seperate topic for JSON messages
    @Bean
    public NewTopic javaguidesJsonTopic(){
        return TopicBuilder
                .name("javaguides_json")
                .build();
    }
    
}
