package net.nwmissouri.springbootkafka;

import net.nwmissouri.springbootkafka.service.ProduceClimateInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class SpringbootKafkaApplication {

	private final Logger logger = LoggerFactory.getLogger(SpringbootKafkaApplication.class);

	@Value("${trigger_time}")
	private final static int timestamp = 5;

	@Autowired
	private ProduceClimateInfo produceClimateInfo;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootKafkaApplication.class, args);
	}

	//60*60*1000
	@Scheduled(fixedRate = 5*60*1000)
	public void postClimateData() {
		produceClimateInfo.produceClimateMessage();
	}
}