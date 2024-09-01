package com.gustavozavaleta.portfolio.votingservice.controllers.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MessageCandidate {
    UUID id;
    String name;
    String party;
    int votes;
}
