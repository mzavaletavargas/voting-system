package com.gustavozavaleta.portfolio.votingservice.services;


import com.gustavozavaleta.portfolio.votingservice.controllers.dto.IdentifyInput;
import com.gustavozavaleta.portfolio.votingservice.model.Users;
import com.gustavozavaleta.portfolio.votingservice.repositories.UsersRepo;
import com.gustavozavaleta.portfolio.votingservice.services.UserService;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
class UserServiceTest {
    UsersRepo usersRepo;
    UserService userService;
    @BeforeEach
    void setup()  {
        usersRepo = Mockito.mock(UsersRepo.class);
        userService = new UserService(usersRepo);
    }

    @Test
    void shouldIdentifyAnUser() {

        IdentifyInput input = new IdentifyInput();
        input.setDocumentId(12345);
        input.setVerificationNumber(67890);

        Users user = new Users();
        user.setNationalId(12345);
        user.setVerificationNumber(67890);

        Mockito.when(usersRepo.findByNationalId(12345)).thenReturn(Optional.of(user));

        Boolean result = userService.identifyUser(input);

        Assertions.assertTrue(result, "should be true if user had been identify");
    }

    @Test
    void shouldNotIdentifyUserIfDocumentIsNull() throws BadRequestException {
        UsersRepo usersRepo = Mockito.mock(UsersRepo.class);
        UserService userService = new UserService(usersRepo);
        IdentifyInput input = new IdentifyInput();
        input.setDocumentId(null);
        input.setVerificationNumber(323);

        Boolean result = userService.identifyUser(input);

        Assertions.assertFalse(result);
    }
}