package com.gustavozavaleta.portfolio.votingservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gustavozavaleta.portfolio.votingservice.controllers.dto.MessageCandidate;
import com.gustavozavaleta.portfolio.votingservice.controllers.dto.MessageEvent;
import com.gustavozavaleta.portfolio.votingservice.model.*;
import com.gustavozavaleta.portfolio.votingservice.repositories.BallotsRepo;
import com.gustavozavaleta.portfolio.votingservice.repositories.CandidateRepo;
import com.gustavozavaleta.portfolio.votingservice.repositories.ResultsRepo;
import com.gustavozavaleta.portfolio.votingservice.repositories.UserElectionRecordRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class BallotsService {

    private final BallotsRepo ballotsRepo;
    private final CandidateRepo candidateRepo;
    private final ResultsRepo resultsRepo;
    private final UserElectionRecordRepo userElectionRecordRepo;
    public BallotsService(BallotsRepo ballotsRepo, CandidateRepo candidateRepo, ResultsRepo resultsRepo, UserElectionRecordRepo userElectionRecordRepo) {
        this.ballotsRepo = ballotsRepo;
        this.candidateRepo = candidateRepo;
        this.resultsRepo = resultsRepo;
        this.userElectionRecordRepo = userElectionRecordRepo;
    }

    @Transactional
    public MessageEvent createBallot(Ballots ballot, Users user) throws JsonProcessingException {
        var candidateId = ballot.getCandidates().getId();
        Candidates candidate = candidateRepo.findById(candidateId).orElseThrow(() -> new IllegalArgumentException("candidate not found"));

        Ballots newBallot = new Ballots();
        newBallot.setCandidates(candidate);
        newBallot.setElectionEvents(candidate.getElectionEvent());
        newBallot.setTimestamp(ballot.getTimestamp());
        ballotsRepo.save(newBallot);

        List<Results> results = resultsRepo.findAllByElectionEvents(candidate.getElectionEvent());


        Optional<Results> resultFiltered = results.stream().filter(result -> result.getCandidates().getId().equals(candidateId)).findFirst();

        Results resultToUpdate = resultFiltered.orElseThrow();
        int candidateVotes = resultToUpdate.getTotalVotes();
        candidateVotes = candidateVotes + 1;
        resultToUpdate.setTotalVotes(candidateVotes);

        resultsRepo.save(resultToUpdate);

        UserElectionRecord electionRecord = new UserElectionRecord();
        electionRecord.setUser(user);
        electionRecord.setElectionEvent(candidate.getElectionEvent());

        userElectionRecordRepo.save(electionRecord);

        var output = results.stream().filter(f -> f.getCandidates().getId() != candidateId);
        List<Results> resultsList = Stream.concat(output, Stream.of(resultToUpdate)).toList();

        int totalVotes = resultsList.stream().reduce(0, (acc, cur) -> acc + cur.getTotalVotes(), Integer::sum);
        MessageEvent messageEvent = new MessageEvent();

        messageEvent.setCandidates(resultsList.stream().map(candi -> {
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
