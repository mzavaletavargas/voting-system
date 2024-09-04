package com.gustavozavaleta.portfolio.votingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Users {
    @Id
    @Column( nullable = false)
    private UUID id;
    private Integer nationalId;
    private Integer verificationNumber;
    private String name;
    private String dateOfBirth;
    private String email;
}
