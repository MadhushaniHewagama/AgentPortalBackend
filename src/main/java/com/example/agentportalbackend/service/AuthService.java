package com.example.agentportalbackend.service;

import com.example.agentportalbackend.dto.TokenDTO;
import com.example.agentportalbackend.dto.User;
import com.example.agentportalbackend.model.Agent;
import com.example.agentportalbackend.model.Application;
import com.example.agentportalbackend.model.DatabaseManager;
import com.example.agentportalbackend.enums.Role;
import com.example.agentportalbackend.repository.AgentRepository;
import com.example.agentportalbackend.repository.ApplicationRepository;
import com.example.agentportalbackend.repository.DatabaseManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
        private DatabaseManagerRepository databaseManagerRepository;

        @Autowired
        private AgentRepository agentRepository;


        public TokenDTO login(User user) throws UsernameNotFoundException {
                Agent agent = agentRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
                TokenDTO tokenDTO;
                if (agent != null) {
                    tokenDTO = new TokenDTO(agent.getId(),Role.Agent);
                }else{
                    throw new UsernameNotFoundException("User not found with username: " + user.getUsername());

                }
            return tokenDTO;
    }


    public TokenDTO otherLogin(User user) {
        DatabaseManager databaseManager = databaseManagerRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        TokenDTO tokenDTO;
        if (databaseManager != null) {
            tokenDTO = new TokenDTO(databaseManager.getId(),databaseManager.getRole());
        }else{
                throw new UsernameNotFoundException("User not found with username: " + user.getUsername());
        }
        return tokenDTO;
    }
}