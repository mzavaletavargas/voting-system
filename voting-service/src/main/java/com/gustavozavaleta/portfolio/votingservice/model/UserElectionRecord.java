package com.gustavozavaleta.portfolio.votingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_election_record", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "election_event_id"})
})
public class UserElectionRecord {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "election_event_id", nullable = false)
    private ElectionEvents electionEvent;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean hasParticipated = true;
}
