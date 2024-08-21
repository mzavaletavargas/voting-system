package com.gustavozavaleta.portfolio.votingservice.util;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}