package com.gustavozavaleta.portfolio.votingservice.repositories;

import com.gustavozavaleta.portfolio.votingservice.model.Candidates;
import com.gustavozavaleta.portfolio.votingservice.model.ElectionEvents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ElectionEventsRepo extends JpaRepository<ElectionEvents, UUID> {
    List<ElectionEvents> findAllByActive(Boolean active);

}
