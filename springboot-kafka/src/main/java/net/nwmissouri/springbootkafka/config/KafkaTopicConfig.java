package net.nwmissouri.springbootkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic maryvillecClimateTopic(){
        return TopicBuilder.name("track_temperatures")
                .partitions(3).build();
    }
}
