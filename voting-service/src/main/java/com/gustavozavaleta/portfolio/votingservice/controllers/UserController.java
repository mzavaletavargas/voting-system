package com.gustavozavaleta.portfolio.votingservice.controllers;

import com.gustavozavaleta.portfolio.votingservice.controllers.dto.IdentifyInput;
import com.gustavozavaleta.portfolio.votingservice.services.JwtService;
import com.gustavozavaleta.portfolio.votingservice.services.UserService;
import com.gustavozavaleta.portfolio.votingservice.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/citizen")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/identify")
    public String identify(@ModelAttribute IdentifyInput identifyInput) {

        if (userService.identifyUser(identifyInput))
            return jwtService.generateToken(identifyInput.getDocumentId());
        else
            throw new UnauthorizedException();

    }

}
