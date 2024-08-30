package com.gustavo.stream_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ResultsController {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ResultsController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/send-message")
    public ResponseEntity<Void> sendMessage(@RequestBody MessagePayload payload) {
        messagingTemplate.convertAndSend("/topic/results", payload);
        return ResponseEntity.noContent().build();
    }
}
