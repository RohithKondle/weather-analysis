package net.nwmissouri.springbootkafka.kafka;

import net.nwmissouri.springbootkafka.model.Data;
import net.nwmissouri.springbootkafka.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private DataService dataService;

    @KafkaListener(topics="track_temperatures", groupId = "myGroup")
    public void consumeTemperatureData(Data climateData){
        logger.info(String.format("Temperature captured for city -> %s",climateData.getCity_name()));

        dataService.saveData(climateData);

        Duration duration = Duration.between(
                climateData.getStartTime(),
                LocalDateTime.now()
        );
        logger.info(String.format("Total time taken for Application flow  in nano seconds is -> %s ",duration.getSeconds()));

    }

    @KafkaListener(topics="track_winds", groupId = "myGroup")
    public void consumeWindsData(Data climateData){
        logger.info(String.format("track_winds captured for city -> %s",climateData.getCity_name()));
        logger.info(String.format("track_winds captured for city at-> %s",climateData.getStartTime()));
        dataService.saveData(climateData);
    }
}
