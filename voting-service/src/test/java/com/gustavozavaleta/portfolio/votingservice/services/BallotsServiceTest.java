package com.gustavozavaleta.portfolio.votingservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    @InjectMocks
    private BallotsService ballotsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBallotSuccessfully() throws JsonProcessingException {
        Ballots ballotX = new Ballots();
        Candidates candidate = new Candidates();
        ElectionEvents electionEvent = new ElectionEvents();
        Results result = new Results();
        result.setTotalVotes(1);
        UserElectionRecord electionRecord = new UserElectionRecord();
        Users user = new Users();
        user.setId(UUID.randomUUID());
        candidate.setId(UUID.randomUUID());
        candidate.setElectionEvent(electionEvent);

        ballotX.setCandidates(candidate);
        ballotX.setTimestamp(new Date());

        when(candidateRepo.findById(any(UUID.class))).thenReturn(Optional.of(candidate));
        when(resultsRepo.findOneByElectionEventsAndCandidates(any(ElectionEvents.class), any(Candidates.class))).thenReturn(result);
//        when(resultsRepo.save(any(Results.class))).thenReturn(result);
//        when(userElectionRecordRepo.save(any(UserElectionRecord.class))).thenReturn(electionRecord);
        MessageEvent messageEvent = ballotsService.createBallot(ballotX, user);
        verify(ballotsRepo, times(1)).save(any(Ballots.class));
        verify(resultsRepo, times(1)).save(any(Results.class));
        verify(userElectionRecordRepo, times(1)).save(any(UserElectionRecord.class));
    }
}