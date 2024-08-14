package com.gustavozavaleta.portfolio.votingservice.repositories;

import com.gustavozavaleta.portfolio.votingservice.model.Candidates;
import com.gustavozavaleta.portfolio.votingservice.model.ElectionEvents;
import com.gustavozavaleta.portfolio.votingservice.model.Parties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CandidateRepo extends JpaRepository<Candidates, UUID> {
    List<Candidates> findAllByElectionEvent(ElectionEvents electionEvent);
}
