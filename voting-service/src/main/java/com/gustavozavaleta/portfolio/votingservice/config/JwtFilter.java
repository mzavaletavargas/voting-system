package com.gustavozavaleta.portfolio.votingservice.config;

import com.gustavozavaleta.portfolio.votingservice.model.UserPrincipal;
import com.gustavozavaleta.portfolio.votingservice.model.Users;
import com.gustavozavaleta.portfolio.votingservice.services.JwtService;
import com.gustavozavaleta.portfolio.votingservice.services.UserService;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Autowired
    ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token;
        String documentId = null;
        try {
        if(authHeader!= null && authHeader.startsWith(("Bearer "))) {
            token = authHeader.substring(7);
            documentId = jwtService.extractDocumentId(token);
        } else {
            token = null;
        }
        Boolean isValidToken = jwtService.isValidJwtFormat(token);
        if(isValidToken && documentId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<Users> user = context.getBean(UserService.class).getUser(Integer.valueOf(documentId));
            if(user.isPresent()) {
                if(jwtService.validateToken(token, user.get())) {

                    UserPrincipal userPrincipal = new UserPrincipal(user.get());
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                }
            }

        }
        } catch (SignatureException ex) {
            System.out.println("Invalid JWT signature: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Invalid JWT token: " + ex.getMessage());
        }
        filterChain.doFilter(request, response);

    }
}
