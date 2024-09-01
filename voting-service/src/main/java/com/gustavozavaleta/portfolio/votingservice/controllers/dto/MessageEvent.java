package com.gustavozavaleta.portfolio.votingservice.controllers.dto;

import lombok.Data;

import java.util.List;

@Data
public class MessageEvent {
    private int totalVotes;
    private List<MessageCandidate> candidates;
}
