package com.gustavozavaleta.portfolio.votingservice.controllers.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MessageEvent {
    private int totalVotes;
    private List<MessageCandidate> candidates;
}
