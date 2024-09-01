package com.gustavozavaleta.portfolio.votingservice.repositories;

import com.gustavozavaleta.portfolio.votingservice.model.Candidates;
import com.gustavozavaleta.portfolio.votingservice.model.ElectionEvents;
import com.gustavozavaleta.portfolio.votingservice.model.Results;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ResultsRepo extends JpaRepository<Results, UUID> {
    Results findOneByElectionEventsAndCandidates(ElectionEvents electionEvents, Candidates candidates);

    List<Results> findAllByElectionEventsAndCandidates(ElectionEvents electionEvent, Candidates candidate);

    List<Results> findAllByElectionEvents(ElectionEvents electionEvent);
}
