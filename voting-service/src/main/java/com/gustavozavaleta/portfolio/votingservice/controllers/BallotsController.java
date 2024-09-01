package com.gustavozavaleta.portfolio.votingservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gustavozavaleta.portfolio.votingservice.controllers.dto.BallotInput;
import com.gustavozavaleta.portfolio.votingservice.controllers.dto.MessageEvent;
import com.gustavozavaleta.portfolio.votingservice.model.UserPrincipal;
import com.gustavozavaleta.portfolio.votingservice.model.Users;
import com.gustavozavaleta.portfolio.votingservice.services.BallotsService;
import com.gustavozavaleta.portfolio.votingservice.services.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/ballots")
public class BallotsController {


    private final BallotsService ballotsService;
    private final KafkaProducer kafkaProducer;

    public BallotsController(BallotsService ballotsService, KafkaProducer kafkaProducer) {
        this.ballotsService = ballotsService;
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping
    public ResponseEntity<Void>  createBallot(@RequestBody BallotInput ballotInput) throws JsonProcessingException {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = userPrincipal.getUser();
        MessageEvent messageEvent = ballotsService.createBallot(ballotInput.toBallot(), user);

        UUID uuid = UUID.randomUUID();


        kafkaProducer.sendEvent(uuid, messageEvent);
        return ResponseEntity.noContent().build();
    }
}
