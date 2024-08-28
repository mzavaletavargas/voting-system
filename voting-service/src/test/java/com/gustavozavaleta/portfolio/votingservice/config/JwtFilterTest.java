package com.gustavozavaleta.portfolio.votingservice.config;

import com.gustavozavaleta.portfolio.votingservice.model.Users;
import com.gustavozavaleta.portfolio.votingservice.services.JwtService;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class JwtFilterTest {

    @Mock
    JwtService jwtService;
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldValidateToken() throws ServletException, IOException {
        String token = "token";
        Users user = new Users();
        user.setNationalId(12345678);

        when(jwtService.extractDocumentId(anyString())).thenReturn("12345678");
        when(jwtService.validateToken(token, user)).thenCallRealMethod();

        boolean isValidToken = jwtService.validateToken(token, user);
        verify(jwtService, times(1)).extractDocumentId(token);
        assertTrue(isValidToken);
    }
}