package com.gustavozavaleta.portfolio.votingservice.services;

import com.gustavozavaleta.portfolio.votingservice.controllers.dto.ElectionEventCandidatesOutput;
import com.gustavozavaleta.portfolio.votingservice.model.Candidates;
import com.gustavozavaleta.portfolio.votingservice.repositories.CandidateRepo;
import com.gustavozavaleta.portfolio.votingservice.repositories.ElectionEventsRepo;
import org.springframework.stereotype.Service;
import com.gustavozavaleta.portfolio.votingservice.model.ElectionEvents;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public List<ElectionEventCandidatesOutput> getCandidatesByElection(String electionEventId) {
        ElectionEvents electionEvent = electionEventsRepo.findById(UUID.fromString(electionEventId)).orElseThrow(() -> new IllegalArgumentException("Election not found"));
        List<Candidates> candidates = electionEvent.getCandidates();
        return candidates.stream()
                .map(candidate -> new ElectionEventCandidatesOutput(
                        candidate.getId(),
                        candidate.getBiography(),
                        candidate.getParty().getName(), // Assuming Party entity has a getName() method
                        candidate.getUsers().getName(), // Assuming Users entity has a getName() method
                        candidate.getLogo(), // Assuming Users entity has a getPhoto() method
                        candidate.getParty().getLogo()
                ))
                .collect(Collectors.toList());
    }
}
