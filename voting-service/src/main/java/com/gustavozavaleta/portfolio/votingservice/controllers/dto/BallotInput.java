package com.gustavozavaleta.portfolio.votingservice.controllers.dto;

import com.gustavozavaleta.portfolio.votingservice.model.Ballots;
import com.gustavozavaleta.portfolio.votingservice.model.Candidates;
import com.gustavozavaleta.portfolio.votingservice.model.ElectionEvents;

import java.util.Date;
import java.util.UUID;

public class BallotInput {
    private UUID candidateId;
    private UUID electionEventId;

    public UUID getElectionEventId() {
        return electionEventId;
    }

    public void setElectionEventId(UUID electionEventId) {
        this.electionEventId = electionEventId;
    }

    public UUID getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(UUID candidateId) {
        this.candidateId = candidateId;
    }

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
