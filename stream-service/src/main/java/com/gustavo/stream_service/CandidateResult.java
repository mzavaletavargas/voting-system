package com.gustavo.stream_service;

import java.util.UUID;

public class CandidateResult {
    private UUID id;
    private String name;
    private String party;
    private long votes;

    // Constructors, getters, and setters
    public CandidateResult() {}

    public CandidateResult(UUID id, String name, String party, long votes) {
        this.id = id;
        this.name = name;
        this.party = party;
        this.votes = votes;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }
}
