package com.gustavo.stream_service;

import lombok.Data;

import java.util.UUID;

@Data
public class MessageCandidate {
    UUID id;
    String name;
    String party;
    int votes;
}
