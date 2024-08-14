package com.gustavozavaleta.portfolio.votingservice.services;

import com.gustavozavaleta.portfolio.votingservice.model.Ballots;
import com.gustavozavaleta.portfolio.votingservice.model.Candidates;
import com.gustavozavaleta.portfolio.votingservice.model.Results;
import com.gustavozavaleta.portfolio.votingservice.repositories.BallotsRepo;
import com.gustavozavaleta.portfolio.votingservice.repositories.CandidateRepo;
import com.gustavozavaleta.portfolio.votingservice.repositories.ResultsRepo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BallotsService {

    private final BallotsRepo ballotsRepo;
    private final CandidateRepo candidateRepo;
    private final ResultsRepo resultsRepo;

    public BallotsService(BallotsRepo ballotsRepo, CandidateRepo candidateRepo, ResultsRepo resultsRepo) {
        this.ballotsRepo = ballotsRepo;
        this.candidateRepo = candidateRepo;
        this.resultsRepo = resultsRepo;
    }

    public Boolean createBallot(Ballots ballotX) {
        var candidateId = ballotX.getCandidates().getId();

        try {
            Candidates candidate = candidateRepo.findById(candidateId).orElseThrow(() -> new IllegalArgumentException("candidate not found"));

            Ballots newBallot = new Ballots();
            newBallot.setCandidates(candidate);
            newBallot.setElectionEvents(candidate.getElectionEvent());
            ballotsRepo.save(newBallot);

            Results result = resultsRepo.findOneByElectionEventsAndCandidates(candidate.getElectionEvent(), candidate);
            int totalVotes = result.getTotalVotes();
            totalVotes = totalVotes + 1;
            result.setTotalVotes(totalVotes);

            resultsRepo.save(result);

            return true;
        } catch( Exception e ) {
            return false;
        }
    }
}
