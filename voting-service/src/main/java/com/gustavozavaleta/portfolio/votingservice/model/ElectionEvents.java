package com.gustavozavaleta.portfolio.votingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class ElectionEvents {
    @Id
    private UUID id;
    private int electionYear;
    private String electionType;
    private String electionName;
    private String electionDescription;
    private String startDate;
    private String endDate;
    @Column(columnDefinition = "boolean default false")
    private Boolean active;
    @OneToMany(mappedBy = "electionEvents", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Ballots> ballots;
    @OneToMany(mappedBy = "electionEvents", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Results> results;
    @OneToMany(mappedBy = "electionEvent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Candidates> candidates;
}
