package com.gustavozavaleta.portfolio.votingservice.controllers;

import com.gustavozavaleta.portfolio.votingservice.controllers.dto.ElectionEventCandidatesOutput;
import com.gustavozavaleta.portfolio.votingservice.controllers.dto.MessageEvent;
import com.gustavozavaleta.portfolio.votingservice.model.Candidates;
import com.gustavozavaleta.portfolio.votingservice.model.ElectionEvents;
import com.gustavozavaleta.portfolio.votingservice.services.ElectionsEventServices;
import com.gustavozavaleta.portfolio.votingservice.services.ResultsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("election-events")
@AllArgsConstructor
public class ElectionsController {

    private final ElectionsEventServices electionsEventServices;
    private final ResultsService resultsService;

    @GetMapping
    public List<ElectionEvents> getActiveElectionEvents() {
        return electionsEventServices.getCurrentElections();
    }

    @GetMapping("/candidates/{electionId}")
    public List<ElectionEventCandidatesOutput> getCandidates(@PathVariable String electionId) {
        return electionsEventServices.getCandidatesByElection(electionId);
    }

    @GetMapping("/{electionId}/results")
    public MessageEvent results(@PathVariable String electionId) {
        return resultsService.getCurrentResults(electionId);
    }
}
