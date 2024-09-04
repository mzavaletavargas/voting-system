package com.gustavozavaleta.portfolio.votingservice.controllers.dto;

import com.gustavozavaleta.portfolio.votingservice.model.Ballots;
import com.gustavozavaleta.portfolio.votingservice.model.Candidates;
import com.gustavozavaleta.portfolio.votingservice.model.ElectionEvents;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class BallotInput {
    private UUID candidateId;
    private UUID electionEventId;

    public Ballots toBallot() {
        var ballot = new Ballots();
        var electionEvent = new ElectionEvents();
        electionEvent.setId(electionEventId);
        ballot.setCandidates(new Candidates());
        ballot.getCandidates().setId(candidateId);
        ballot.setElectionEvents(electionEvent);
        ballot.setTimestamp(new Date(System.currentTimeMillis()));
        return ballot;
    }
}
