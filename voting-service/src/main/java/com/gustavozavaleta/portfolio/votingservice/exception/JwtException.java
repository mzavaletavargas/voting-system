package com.gustavozavaleta.portfolio.votingservice.exception;

public class JwtException extends RuntimeException {
    public JwtException(Throwable e) {
        super(e);
    }
}
