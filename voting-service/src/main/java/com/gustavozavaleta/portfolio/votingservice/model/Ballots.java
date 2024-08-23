package com.gustavozavaleta.portfolio.votingservice.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@Scope("prototype")
@Entity
public class Ballots {

    @Id
    @GeneratedValue
    private UUID id;
    private Date timestamp;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private ElectionEvents electionEvents;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Candidates candidates;


    public ElectionEvents getElectionEvents() {
        return electionEvents;
    }

    public void setElectionEvents(ElectionEvents electionEvents) {
        this.electionEvents = electionEvents;
    }

    public Candidates getCandidates() {
        return candidates;
    }

    public void setCandidates(Candidates candidates) {
        this.candidates = candidates;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
