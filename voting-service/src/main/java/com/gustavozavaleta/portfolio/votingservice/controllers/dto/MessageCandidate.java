package com.gustavozavaleta.portfolio.votingservice.controllers.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MessageCandidate {
    UUID id;
    String name;
    String party;
    int votes;
}
