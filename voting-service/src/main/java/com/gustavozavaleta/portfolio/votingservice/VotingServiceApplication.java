package com.gustavozavaleta.portfolio.votingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class VotingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotingServiceApplication.class, args);
    }

}
