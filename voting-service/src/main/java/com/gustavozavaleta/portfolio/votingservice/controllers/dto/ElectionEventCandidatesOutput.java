package com.gustavozavaleta.portfolio.votingservice.controllers.dto;


import java.util.UUID;

public class ElectionEventCandidatesOutput {
    private UUID id;
    private String biography;
    private String party;
    private String name;
    private String photo;
    private String partyLogo;

    // Constructors
    public ElectionEventCandidatesOutput(UUID id, String biography, String party, String name, String photo, String partyLogo) {
        this.id = id;
        this.biography = biography;
        this.party = party;
        this.name = name;
        this.photo = photo;
        this.partyLogo = partyLogo;
    }

    // Getters and Setters
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

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPartyLogo() {
        return partyLogo;
    }

    public void setPartyLogo(String partyLogo) {
        this.partyLogo = partyLogo;
    }
}