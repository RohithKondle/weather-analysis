package net.nwmissouri.springbootkafka.kafka;

import net.nwmissouri.springbootkafka.model.Climate;
import net.nwmissouri.springbootkafka.model.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KafkaProducer {

    private KafkaTemplate<String, Data> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    public KafkaProducer(KafkaTemplate<String, Data> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessageforTemperatures(Climate climate){

        //Add Current timestamp to the dataset
        climate.getData().get(0).setLocalDateTime(LocalDateTime.now());

        // Converting Celcius to farenhiet
        climate.getData().get(0).setTemp((climate.getData().get(0).getTemp()* 1.8) + 32.0 );

        kafkaTemplate.send("track_temperatures",climate.getData().get(0));
        logger.info(String.format("Message published for city -> %s", climate.getData().get(0).getCity_name()));
    }

    public void sendMessageforWinds(Climate body) {
        body.getData().get(0).setLocalDateTime(LocalDateTime.now());

        kafkaTemplate.send("track_winds",body.getData().get(0));
        logger.info(String.format("Message published for city wind-> %s", body.getData().get(0).getCity_name()));
        logger.info(String.format("Message published for city at -> %s", body.getData().get(0).getStartTime()));

    }
}
