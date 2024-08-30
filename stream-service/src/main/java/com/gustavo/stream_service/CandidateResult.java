package com.gustavo.stream_service;

import java.util.UUID;

public class CandidateResult {
    private UUID candidateId;
    private String candidateName;
    private String partyName;
    private long votes;

    // Constructors, getters, and setters
    public CandidateResult() {}

    public CandidateResult(UUID candidateId, String candidateName, String partyName, long votes) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.partyName = partyName;
        this.votes = votes;
    }

    public UUID getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(UUID candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }
}
