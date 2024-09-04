package com.gustavozavaleta.portfolio.votingservice.controllers.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ElectionEventCandidatesOutput {
    private UUID id;
    private String biography;
    private String party;
    private String name;
    private String photo;
    private String partyLogo;
}