package com.gustavozavaleta.portfolio.votingservice.controllers;

import com.gustavozavaleta.portfolio.votingservice.controllers.dto.IdentifyInput;
import com.gustavozavaleta.portfolio.votingservice.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/citizen")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/identify")
    public Boolean identify(@RequestBody IdentifyInput identifyInput) {
        return userService.idenitfyUser(identifyInput);
    }

}
