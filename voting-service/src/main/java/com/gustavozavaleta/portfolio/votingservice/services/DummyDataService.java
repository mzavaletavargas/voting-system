package com.gustavozavaleta.portfolio.votingservice.services;

import com.gustavozavaleta.portfolio.votingservice.model.*;
import com.gustavozavaleta.portfolio.votingservice.repositories.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class DummyDataService {

    @Autowired
    private final PartiesRepo partiesRepo;

    @Autowired
    private final CandidateRepo candidatesRepo;

    @Autowired
    private final UsersRepo usersRepo;

    @Autowired
    private final ElectionEventsRepo electionEventsRepo;
    @Autowired
    private ResultsRepo resultsRepo;

    @Autowired
    public DummyDataService(PartiesRepo partiesRepo, CandidateRepo candidatesRepo, UsersRepo usersRepo, ElectionEventsRepo electionEventsRepo) {
        this.partiesRepo = partiesRepo;
        this.candidatesRepo = candidatesRepo;
        this.usersRepo = usersRepo;
        this.electionEventsRepo = electionEventsRepo;
    }

    public <T> void saveIfNotExists(JpaRepository<T, UUID> repository, UUID id, T entity) {
        System.out.println(id);
        Optional<T> existingEntity = repository.findById(id);
        if (existingEntity.isEmpty()) {
            repository.save(entity);
        }
    }
    @PostConstruct
    public void insertDummyData() {
        try {
            insertDataWithTransaction();
            System.out.println("Dummy data inserted successfully.");
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace to understand the issue
        }
    }


    public void insertDataWithTransaction() {
        try {
            UUID electionId = UUID.fromString("3caf15f5-c58c-4146-b095-f285c08f5556");
            ElectionEvents electionEvent = new ElectionEvents();
            electionEvent.setId(electionId);
            electionEvent.setActive(true);
            electionEvent.setElectionName("Election 2024");
            electionEvent.setElectionYear(2024);
            electionEvent.setElectionType("PRESIDENTIAL");
            electionEvent.setStartDate("28-07-2024");
            electionEvent.setEndDate("30-07-2024");

            saveIfNotExists(electionEventsRepo, electionId, electionEvent);

            UUID userId1 = UUID.fromString("4caf15f5-c58c-4146-b095-f285c08f5556");

            Users user = new Users();
            user.setId(userId1);
            user.setEmail("user@gmail.com");
            user.setNationalId(76663232);
            user.setVerificationNumber(1);
            user.setName("demo user");
            saveIfNotExists(usersRepo, userId1, user);

            UUID userId2 = UUID.fromString("5caf15f5-c58c-4146-b095-f285c08f5556");

            Users user2 = new Users();
            user2.setId(userId2);
            user2.setEmail("user2@gmail.com");
            user2.setNationalId(16663232);
            user2.setVerificationNumber(2);
            user2.setName("second demo user");
            saveIfNotExists(usersRepo, userId2, user2);

            UUID partyId1 = UUID.fromString("01173861-debb-4de4-80f4-01d3a66b6c36");
            Parties party = new Parties();
            party.setId(partyId1);
            party.setName("party 1");
            party.setLogo("https://placehold.co/400x400");
            party.setFoundationDate(new Date().toString());
            saveIfNotExists(partiesRepo, partyId1, party);

            UUID partyId2 = UUID.fromString("91173861-debb-4de4-80f4-01d3a66b6c36");

            Parties party2 = new Parties();
            party2.setId(partyId2);
            party2.setName("party 2");
            party2.setLogo("https://placehold.co/400x400");
            party2.setFoundationDate(new Date().toString());
            saveIfNotExists(partiesRepo, partyId2, party2);

            var candidateId1 = UUID.fromString("5066c80a-1f38-4ea9-a559-8e9e2b1b938c");
            Candidates candidate = new Candidates();
            candidate.setId(candidateId1);
            candidate.setParty(party);
            candidate.setUsers(user);
            candidate.setLogo("https://placehold.co/400x400");
            candidate.setElectionEvent(electionEvent);
            saveIfNotExists(candidatesRepo, candidateId1, candidate);

            UUID candidateId2 = UUID.fromString("7066c80a-1f38-4ea9-a559-8e9e2b1b938c");

            Candidates candidate2 = new Candidates();
            candidate2.setId(candidateId2);
            candidate2.setElectionEvent(electionEvent);
            candidate2.setParty(party2);
            candidate.setLogo("https://placehold.co/400x400");
            candidate2.setUsers(user2);
            saveIfNotExists(candidatesRepo, candidateId2, candidate2);

            UUID results1Id = UUID.fromString("1236c80a-1f38-4ea9-a559-8e9e2b1b938c");

            Results results = new Results();
            results.setId(results1Id);
            results.setTotalVotes(25);
            results.setCandidates(candidate);
            results.setElectionEvents(electionEvent);
            saveIfNotExists(resultsRepo, results1Id, results);

            UUID results2Id = UUID.fromString("5436c80a-1f38-4ea9-a559-8e9e2b1b938c");

            Results results2= new Results();
            results2.setId(results2Id);
            results2.setTotalVotes(65);
            results2.setCandidates(candidate2);
            results2.setElectionEvents(electionEvent);
            saveIfNotExists(resultsRepo, results2Id, results2);


            System.out.println("Dummy data inserted successfully.");
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace to understand the issue
        }
    }
}
