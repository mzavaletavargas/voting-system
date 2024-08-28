package com.gustavozavaleta.portfolio.votingservice.services;

import com.gustavozavaleta.portfolio.votingservice.model.*;
import com.gustavozavaleta.portfolio.votingservice.repositories.BallotsRepo;
import com.gustavozavaleta.portfolio.votingservice.repositories.CandidateRepo;
import com.gustavozavaleta.portfolio.votingservice.repositories.ResultsRepo;
import com.gustavozavaleta.portfolio.votingservice.repositories.UserElectionRecordRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void createBallot(Ballots ballot, Users user) {
        var candidateId = ballot.getCandidates().getId();
        Candidates candidate = candidateRepo.findById(candidateId).orElseThrow(() -> new IllegalArgumentException("candidate not found"));

        Ballots newBallot = new Ballots();
        newBallot.setCandidates(candidate);
        newBallot.setElectionEvents(candidate.getElectionEvent());
        newBallot.setTimestamp(ballot.getTimestamp());
        ballotsRepo.save(newBallot);

        Results result = resultsRepo.findOneByElectionEventsAndCandidates(candidate.getElectionEvent(), candidate);
        int totalVotes = result.getTotalVotes();
        totalVotes = totalVotes + 1;
        result.setTotalVotes(totalVotes);

        resultsRepo.save(result);


        UserElectionRecord electionRecord = new UserElectionRecord();
        electionRecord.setUser(user);
        electionRecord.setElectionEvent(candidate.getElectionEvent());

        userElectionRecordRepo.save(electionRecord);

    }
}
