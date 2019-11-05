package com.tactical.web.rest;

import com.tactical.service.Bii1TacticalApplicationKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bii-1-tactical-application-kafka")
public class Bii1TacticalApplicationKafkaResource {

    private final Logger log = LoggerFactory.getLogger(Bii1TacticalApplicationKafkaResource.class);

    private Bii1TacticalApplicationKafkaProducer kafkaProducer;

    public Bii1TacticalApplicationKafkaResource(Bii1TacticalApplicationKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        log.debug("REST request to send to Kafka topic the message : {}", message);
        this.kafkaProducer.send(message);
    }
}
