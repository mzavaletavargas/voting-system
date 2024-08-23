package com.gustavozavaleta.portfolio.votingservice.repositories;

import com.gustavozavaleta.portfolio.votingservice.model.UserElectionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserElectionRecordRepo extends JpaRepository<UserElectionRecord, UUID> {
}
