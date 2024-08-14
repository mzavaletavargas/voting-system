package com.gustavozavaleta.portfolio.votingservice.controllers.dto;

import com.gustavozavaleta.portfolio.votingservice.model.Ballots;
import com.gustavozavaleta.portfolio.votingservice.model.Candidates;

import java.util.UUID;

public class BallotInput {
    private String candidateId;
    private Boolean token;

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public Boolean getToken() {
        return token;
    }

    public void setToken(Boolean token) {
        this.token = token;
    }

    public Ballots toBallot() {
        var ballot = new Ballots();
        ballot.setCandidates(new Candidates());
        ballot.getCandidates().setId(UUID.fromString(candidateId));
        return ballot;
    }
}
