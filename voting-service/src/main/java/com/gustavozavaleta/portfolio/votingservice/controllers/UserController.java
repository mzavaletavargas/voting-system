package com.gustavozavaleta.portfolio.votingservice.controllers;

import com.gustavozavaleta.portfolio.votingservice.controllers.dto.IdentifyInput;
import com.gustavozavaleta.portfolio.votingservice.services.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController()
@RequestMapping("/citizen")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/identify")
    public Boolean identify(@ModelAttribute IdentifyInput identifyInput) {

        return userService.identifyUser(identifyInput);

    }

}
