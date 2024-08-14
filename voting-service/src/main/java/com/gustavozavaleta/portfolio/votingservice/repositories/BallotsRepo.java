package com.gustavozavaleta.portfolio.votingservice.repositories;

import com.gustavozavaleta.portfolio.votingservice.model.Ballots;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BallotsRepo extends JpaRepository<Ballots, UUID> {

}
