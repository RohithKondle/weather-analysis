package net.nwmissouri.springbootkafka.service;

import net.nwmissouri.springbootkafka.kafka.KafkaProducer;
import net.nwmissouri.springbootkafka.model.Climate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProduceClimateInfo {

    private final Logger logger = LoggerFactory.getLogger(ProduceClimateInfo.class);

    @Value("${city_list}")
    private String[] citylist;

    @Autowired
    private ClimateAPI climateAPI;

    @Autowired
    private KafkaProducer kafkaProducer;

    public void produceClimateMessage() {

        for (String eachCity : citylist) {
            ResponseEntity<Climate> response = climateAPI.getClimateData(eachCity);

            if (response.getStatusCode() == HttpStatus.OK) {
               kafkaProducer.sendMessageforTemperatures(response.getBody());
            } else {
                logger.info("Failed to get data from climate api");
            }
        }
    }
}
