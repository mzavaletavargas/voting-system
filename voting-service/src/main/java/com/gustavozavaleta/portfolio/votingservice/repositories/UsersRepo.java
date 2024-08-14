package com.gustavozavaleta.portfolio.votingservice.repositories;

import com.gustavozavaleta.portfolio.votingservice.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepo extends JpaRepository<Users, UUID> {

    Optional<Users> findByNationalId(Number nationalId);
}
