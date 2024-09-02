package com.gustavozavaleta.portfolio.votingservice.services;

import com.gustavozavaleta.portfolio.votingservice.controllers.dto.MessageEvent;
import com.gustavozavaleta.portfolio.votingservice.model.*;
import com.gustavozavaleta.portfolio.votingservice.repositories.BallotsRepo;
import com.gustavozavaleta.portfolio.votingservice.repositories.CandidateRepo;
import com.gustavozavaleta.portfolio.votingservice.repositories.ResultsRepo;
import com.gustavozavaleta.portfolio.votingservice.repositories.UserElectionRecordRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BallotsServiceTest {
    @Mock
    private BallotsRepo ballotsRepo;

    @Mock
    private CandidateRepo candidateRepo;

    @Mock
    private ResultsRepo resultsRepo;

    @Mock
    private UserElectionRecordRepo userElectionRecordRepo;

    @Mock
    private KafkaProducer kafkaProducer;

    @InjectMocks
    private BallotsService ballotsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBallotSuccessfully() {
        Ballots ballotX = new Ballots();
        Candidates candidate = new Candidates();
        ElectionEvents electionEvent = new ElectionEvents();
        Users user = new Users();
        Results result = new Results();
        result.setTotalVotes(1);
        UserElectionRecord electionRecord = new UserElectionRecord();

        candidate.setId(UUID.randomUUID());
        candidate.setElectionEvent(electionEvent);

        ballotX.setCandidates(candidate);
        ballotX.setTimestamp(new Date());

        when(candidateRepo.findById(any(UUID.class))).thenReturn(Optional.of(candidate));
        when(resultsRepo.findOneByElectionEventsAndCandidates(any(ElectionEvents.class), any(Candidates.class))).thenReturn(result);
//        Mockito.doNothing().when(kafkaProducer).sendEvent(any(MessageEvent.class));
//        ballotsService.createBallot(ballotX, user);

        verify(ballotsRepo, times(1)).save(any(Ballots.class));
        verify(resultsRepo, times(1)).save(any(Results.class));
        verify(userElectionRecordRepo, times(1)).save(any(UserElectionRecord.class));
    }
}