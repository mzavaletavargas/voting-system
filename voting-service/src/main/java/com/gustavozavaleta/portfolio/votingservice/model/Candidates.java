package com.gustavozavaleta.portfolio.votingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


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
    private List<Ballots> ballots;
    @OneToMany(mappedBy = "candidates", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Results> results;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @JsonIgnore
    private ElectionEvents electionEvent;

    public ElectionEvents getElectionEvent() {
        return electionEvent;
    }

    public void setElectionEvent(ElectionEvents electionEvent) {
        this.electionEvent = electionEvent;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Parties getParty() {
        return party;
    }

    public void setParty(Parties party) {
        this.party = party;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }


}
