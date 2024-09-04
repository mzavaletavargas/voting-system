package com.gustavozavaleta.portfolio.votingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;


@Entity
@Getter
@Setter
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
}
