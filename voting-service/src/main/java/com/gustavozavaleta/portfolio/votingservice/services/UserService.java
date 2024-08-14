package com.gustavozavaleta.portfolio.votingservice.services;

import com.gustavozavaleta.portfolio.votingservice.controllers.dto.IdentifyInput;
import com.gustavozavaleta.portfolio.votingservice.model.Users;
import com.gustavozavaleta.portfolio.votingservice.repositories.UsersRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    UsersRepo usersRepo;

    UserService(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    public Boolean idenitfyUser(IdentifyInput input) {

        Optional<Users> user =  this.usersRepo.findByNationalId(input.documentId);
        if(user.isPresent()) {
            return input.verificationNumber.equals(user.get().getVerificationNumber());
        }
        return false;
    }
}
