package com.gustavo.stream_service;

import lombok.Data;

import java.util.List;

@Data
public class MessageEvent {
    private int totalVotes;
    private List<MessageCandidate> candidates;
}
