package com.gustavozavaleta.portfolio.votingservice.controllers;

import com.gustavozavaleta.portfolio.votingservice.controllers.dto.IdentifyInput;
import com.gustavozavaleta.portfolio.votingservice.services.JwtService;
import com.gustavozavaleta.portfolio.votingservice.services.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

        if(userService.identifyUser(identifyInput))
         return jwtService.generateToken(identifyInput.getDocumentId());
        else
        return "no";

//        return

    }

}
