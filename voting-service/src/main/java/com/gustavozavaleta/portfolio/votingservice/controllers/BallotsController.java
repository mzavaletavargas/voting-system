package com.gustavozavaleta.portfolio.votingservice.controllers;

import com.gustavozavaleta.portfolio.votingservice.controllers.dto.BallotInput;
import com.gustavozavaleta.portfolio.votingservice.services.BallotsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ballots")
public class BallotsController {


    private final BallotsService ballotsService;

    public BallotsController(BallotsService ballotsService) {
        this.ballotsService = ballotsService;
    }

    @PostMapping
    public Boolean createBallot(@RequestBody BallotInput ballotInput) {


            Boolean response = ballotsService.createBallot(ballotInput.toBallot());

            return true;

    }
}
