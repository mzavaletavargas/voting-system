package com.gustavozavaleta.portfolio.votingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Parties {

    @Id
    private UUID id;
    private String name;
    private String foundationDate;
    private String logo;
    @OneToMany(mappedBy = "party", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Candidates> candidates;
}
