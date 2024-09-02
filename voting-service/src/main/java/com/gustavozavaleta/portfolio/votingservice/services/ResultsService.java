package com.gustavozavaleta.portfolio.votingservice.services;

import com.gustavozavaleta.portfolio.votingservice.controllers.dto.MessageCandidate;
import com.gustavozavaleta.portfolio.votingservice.controllers.dto.MessageEvent;
import com.gustavozavaleta.portfolio.votingservice.model.ElectionEvents;
import com.gustavozavaleta.portfolio.votingservice.model.Results;
import com.gustavozavaleta.portfolio.votingservice.repositories.ResultsRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ResultsService {
    private final ResultsRepo resultsRepo;

    public ResultsService(ResultsRepo resultsRepo) {
        this.resultsRepo = resultsRepo;
    }

    public MessageEvent getCurrentResults(String eventId) {
        UUID electionEventId = UUID.fromString(eventId);
        MessageEvent messageEvent = new MessageEvent();
        ElectionEvents electionEvents = new ElectionEvents();
        electionEvents.setId(electionEventId);
        List<Results> results = resultsRepo.findAllByElectionEvents(electionEvents);

        int totalVotes = results.stream().reduce(0, (acc, cur) -> acc + cur.getTotalVotes(), Integer::sum);

        messageEvent.setCandidates(results.stream().map(candi -> {
            MessageCandidate messageCandidate = new MessageCandidate();
            messageCandidate.setId(candi.getCandidates().getId());
            messageCandidate.setName(candi.getCandidates().getUsers().getName());
            messageCandidate.setParty(candi.getCandidates().getParty().getName());
            messageCandidate.setVotes(candi.getTotalVotes());
            return messageCandidate;
        }).toList());
        messageEvent.setTotalVotes(totalVotes);
        return messageEvent;
    }
}
