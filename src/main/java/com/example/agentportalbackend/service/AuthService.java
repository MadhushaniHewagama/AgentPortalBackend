package com.example.agentportalbackend.service;

import com.example.agentportalbackend.dto.Token;
import com.example.agentportalbackend.dto.User;
import com.example.agentportalbackend.model.Agent;
import com.example.agentportalbackend.model.DatabaseManager;
import com.example.agentportalbackend.repository.AgentRepository;
import com.example.agentportalbackend.repository.DatabaseManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {
        @Autowired
        private DatabaseManagerRepository databaseManagerRepository;

        @Autowired
        private AgentRepository agentRepository;


        public Token login(User user) throws UsernameNotFoundException {
                DatabaseManager databaseManager = databaseManagerRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
                Token token;
                if (databaseManager != null) {
                    token = new Token(databaseManager.getId(),"DBM");
                }else{
                    Agent agent = agentRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
                    if (agent != null) {
                        token = new Token( agent.getId(),"Agent");
                    }else{
                        throw new UsernameNotFoundException("User not found with username: " + user.getUsername());
                    }
                }
            return token;
    }

    public Agent register(Agent agent) {
        log.info("Register:::");
        Agent savedUser = agentRepository.save(agent);
        return savedUser;
    }

}