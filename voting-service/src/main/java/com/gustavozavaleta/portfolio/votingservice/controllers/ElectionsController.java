package com.gustavozavaleta.portfolio.votingservice.controllers;

import com.gustavozavaleta.portfolio.votingservice.controllers.dto.ElectionEventCandidatesOutput;
import com.gustavozavaleta.portfolio.votingservice.model.Candidates;
import com.gustavozavaleta.portfolio.votingservice.model.ElectionEvents;
import com.gustavozavaleta.portfolio.votingservice.services.ElectionsEventServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("election-events")
public class ElectionsController {

    private final ElectionsEventServices electionsEventServices;

    public ElectionsController(ElectionsEventServices electionsEventServices) {
        this.electionsEventServices = electionsEventServices;
    }

    @GetMapping
    public List<ElectionEvents> getActiveElectionEvents() {
        return electionsEventServices.getCurrentElections();
    }

    @GetMapping("/candidates")
    public List<ElectionEventCandidatesOutput> getCandidates(@RequestParam String electionId) {
        List<ElectionEventCandidatesOutput> candidates = electionsEventServices.getCandidatesByElection(electionId);
        return candidates;
//        return electionsEventServices.getCandidatesByElection(electionId);
    }


}
