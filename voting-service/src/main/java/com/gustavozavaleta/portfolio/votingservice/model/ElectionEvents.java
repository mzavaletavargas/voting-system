package com.gustavozavaleta.portfolio.votingservice.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


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
    private List<Ballots> ballots;
    @OneToMany(mappedBy = "electionEvents", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Results> results;
    @OneToMany(mappedBy = "electionEvent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Candidates> candidates;

    public List<Candidates> getCandidates() {
        return candidates;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getElectionYear() {
        return electionYear;
    }

    public void setElectionYear(int electionYear) {
        this.electionYear = electionYear;
    }

    public String getElectionType() {
        return electionType;
    }

    public void setElectionType(String electionType) {
        this.electionType = electionType;
    }

    public String getElectionName() {
        return electionName;
    }

    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }

    public String getElectionDescription() {
        return electionDescription;
    }

    public void setElectionDescription(String electionDescription) {
        this.electionDescription = electionDescription;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
