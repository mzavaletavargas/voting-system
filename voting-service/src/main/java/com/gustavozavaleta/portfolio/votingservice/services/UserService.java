package com.gustavozavaleta.portfolio.votingservice.services;

import com.gustavozavaleta.portfolio.votingservice.controllers.dto.IdentifyInput;
import com.gustavozavaleta.portfolio.votingservice.model.Users;
import com.gustavozavaleta.portfolio.votingservice.repositories.UsersRepo;
import com.gustavozavaleta.portfolio.votingservice.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    UsersRepo usersRepo;

    public UserService(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    public Optional<Users> getUser(Number documentId) {
        return this.usersRepo.findByNationalId(documentId);
    }
    public Boolean identifyUser(IdentifyInput input) throws UserNotFoundException {

        Optional<Users> user =  getUser(input.getDocumentId());

        Users foundUser = user.orElseThrow(() -> new UserNotFoundException("User with document ID " + input.getDocumentId() + " not found."));

        return input.getVerificationNumber().equals(foundUser.getVerificationNumber());

    }
}
