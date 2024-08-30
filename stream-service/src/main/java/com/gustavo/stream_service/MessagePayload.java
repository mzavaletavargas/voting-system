package com.gustavo.stream_service;

import java.util.List;

public class MessagePayload {
    private long totalVoters;
    private long totalVotes;
    private long blankVotes;
    private List<CandidateResult> candidates;

    // Constructors, getters, and setters
    public MessagePayload() {}

    public MessagePayload(long totalVoters, long totalVotes, long blankVotes, List<CandidateResult> candidates) {
        this.totalVoters = totalVoters;
        this.totalVotes = totalVotes;
        this.blankVotes = blankVotes;
        this.candidates = candidates;
    }

    public long getTotalVoters() {
        return totalVoters;
    }

    public void setTotalVoters(long totalVoters) {
        this.totalVoters = totalVoters;
    }

    public long getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(long totalVotes) {
        this.totalVotes = totalVotes;
    }

    public long getBlankVotes() {
        return blankVotes;
    }

    public void setBlankVotes(long blankVotes) {
        this.blankVotes = blankVotes;
    }

    public List<CandidateResult> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<CandidateResult> candidates) {
        this.candidates = candidates;
    }
}


