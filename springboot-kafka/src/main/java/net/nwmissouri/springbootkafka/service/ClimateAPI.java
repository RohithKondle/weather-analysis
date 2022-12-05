package net.nwmissouri.springbootkafka.service;

import net.nwmissouri.springbootkafka.model.Climate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ClimateAPI {

    private final Logger logger = LoggerFactory.getLogger(ClimateAPI.class);

    @Autowired
    private Environment env;

    @Value("${uri_key}")
    private String cliamte_key;

    @Value("${uri_key_value}")
    private String climate_key_value;

    @Value("${uri_host}")
    private String climate_host;

    @Value("${uri_host_value}")
    private String climate_host_value;

    @Value("${climate_endpoint}")
    private String climate_endpoint;

    HttpHeaders headers;

    String formedUrl="";

    public ResponseEntity<Climate> getClimateData(String cityName){
        LocalDateTime localDateTime = LocalDateTime.now();
        RestTemplate restTemplate = new RestTemplate();
        mapHeadersAndVariables(cityName);
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<Climate> response = restTemplate.exchange(formedUrl, HttpMethod.GET, request, Climate.class);

        response.getBody().getData().get(0).setStartTime(localDateTime);
        return response;
    }

    private void mapHeadersAndVariables(String cityName) {
        headers = new HttpHeaders();
        headers.set(cliamte_key,climate_key_value);
        headers.set(climate_host,climate_host_value);

        formedUrl = climate_endpoint;
        formedUrl = formedUrl+"lon="+env.getProperty(cityName+"_longitude")+"&lat="+env.getProperty(cityName+"_latitude");
     }
}
