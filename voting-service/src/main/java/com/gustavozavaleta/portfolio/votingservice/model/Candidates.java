package com.gustavozavaleta.portfolio.votingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Candidates {
    @Id
    private UUID id;
    private String biography;
    private String logo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @JsonIgnore
    private Parties party;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn( nullable = false)
    @JsonIgnore
    private Users users;
    @OneToMany(mappedBy = "candidates", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Ballots> ballots;
    @OneToMany(mappedBy = "candidates", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Results> results;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @JsonIgnore
    private ElectionEvents electionEvent;




}
