package com.example.agentportalbackend.service;

import com.example.agentportalbackend.dto.Token;
import com.example.agentportalbackend.model.Agent;
import com.example.agentportalbackend.model.DatabaseManager;
import com.example.agentportalbackend.repository.AgentRepository;
import com.example.agentportalbackend.repository.DatabaseManagerRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AgenntService {

    @Autowired
    private AgentRepository agentRepository;
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public Agent getProfile(String jwt) throws UsernameNotFoundException {
        Token token;
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).getBody();
        log.info("ID {}", claims.get("id"));

        Long userId = Long.parseLong(claims.get("id").toString());
        Agent agent = agentRepository.findById(userId).orElse(null);
        if(agent == null){
            throw new UsernameNotFoundException("Invalid ID");
        }
        return agent;
    }

    public Agent updateProfile(Agent agent) {
        agentRepository.updateUsernameAndPasswordAndFirstnameAndMiddlenameAndLastnameAndGenderAndDateOfBirthAndSsnById(agent.getUsername(), agent.getPassword(), agent.getFirstname(),agent.getMiddlename(),agent.getLastname(),agent.getGender(),agent.getDateOfBirth(),agent.getSsn(),agent.getId());
        return agent;
    }
}