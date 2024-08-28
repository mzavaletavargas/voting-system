package com.gustavozavaleta.portfolio.votingservice.controllers;

import com.gustavozavaleta.portfolio.votingservice.controllers.dto.BallotInput;
import com.gustavozavaleta.portfolio.votingservice.model.UserPrincipal;
import com.gustavozavaleta.portfolio.votingservice.model.Users;
import com.gustavozavaleta.portfolio.votingservice.services.BallotsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ballots")
public class BallotsController {


    private final BallotsService ballotsService;

    public BallotsController(BallotsService ballotsService) {
        this.ballotsService = ballotsService;
    }

    @PostMapping
    public ResponseEntity<Void>  createBallot(@RequestBody BallotInput ballotInput) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = userPrincipal.getUser();
        ballotsService.createBallot(ballotInput.toBallot(), user);

        return ResponseEntity.noContent().build();
    }
}
