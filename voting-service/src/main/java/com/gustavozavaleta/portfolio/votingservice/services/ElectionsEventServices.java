package com.gustavozavaleta.portfolio.votingservice.services;

import com.gustavozavaleta.portfolio.votingservice.model.Candidates;
import com.gustavozavaleta.portfolio.votingservice.repositories.CandidateRepo;
import com.gustavozavaleta.portfolio.votingservice.repositories.ElectionEventsRepo;
import org.springframework.stereotype.Service;
import com.gustavozavaleta.portfolio.votingservice.model.ElectionEvents;

import java.util.List;
import java.util.UUID;

@Service
public class ElectionsEventServices {

    private final ElectionEventsRepo electionEventsRepo;
    private final CandidateRepo candidateRepo;

    public ElectionsEventServices(ElectionEventsRepo electionEventsRepo, CandidateRepo candidateRepo) {
        this.electionEventsRepo = electionEventsRepo;
        this.candidateRepo = candidateRepo;
    }

    public List<ElectionEvents> getCurrentElections() {
        return electionEventsRepo.findAllByActive(true);
    }

    public List<Candidates> getCandidatesByElection(String electionEventId) {
        ElectionEvents electionEvent = electionEventsRepo.findById(UUID.fromString(electionEventId)).orElseThrow(() -> new IllegalArgumentException("Election not found"));

        return candidateRepo.findAllByElectionEvent(electionEvent);
    }
}
