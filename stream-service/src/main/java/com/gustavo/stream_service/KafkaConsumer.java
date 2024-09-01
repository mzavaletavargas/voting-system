package com.gustavo.stream_service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class KafkaConsumer {
    private final SimpMessagingTemplate messagingTemplate;

    public KafkaConsumer(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "voting-system.events.presidential-2024", groupId = "my-group")
    public void messageConsumer(@Payload MessageEvent message, ConsumerRecord<String, MessageEvent> record,
                                Acknowledgment ack) {
        try {
            System.out.println("Received event: " + message);
            messagingTemplate.convertAndSend("/topic/results", message);
            ack.acknowledge();
        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
            System.err.println("Problematic record: " + record);
        }

    }


}