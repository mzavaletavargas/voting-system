package com.gustavozavaleta.portfolio.votingservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Results {
    @Id
    private UUID id;
    private int totalVotes;
    private String lastUpdated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private ElectionEvents electionEvents;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Candidates candidates;
}
