package com.gustavozavaleta.portfolio.votingservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gustavozavaleta.portfolio.votingservice.controllers.dto.MessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {
    @Autowired
    private ObjectMapper objectMapper;

    public static final String TOPIC = "voting-system.events.presidential-2024";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendEvent(UUID id, MessageEvent messageEvent) throws JsonProcessingException {
        String eventAsString = objectMapper.writeValueAsString(messageEvent);

        kafkaTemplate.send(TOPIC, id.toString() , eventAsString);
        log.info("Producer produced the message {}", messageEvent);
    }


}