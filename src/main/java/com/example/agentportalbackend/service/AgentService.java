package com.example.agentportalbackend.service;

import com.example.agentportalbackend.dto.ErrorDTO;
import com.example.agentportalbackend.dto.PasswordDTO;
import com.example.agentportalbackend.dto.SuccessDTO;
import com.example.agentportalbackend.dto.TokenDTO;
import com.example.agentportalbackend.enums.ApplicationStatus;
import com.example.agentportalbackend.model.Agent;
import com.example.agentportalbackend.repository.AgentRepository;
import com.example.agentportalbackend.repository.ApplicationRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AgentService {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private AgentRepository agentRepository;
//    @Value("${jwt.secret}")
//    private String SECRET_KEY;
//
//    public Agent getProfile(String jwt) throws UsernameNotFoundException {
//        TokenDTO tokenDTO;
//        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).getBody();
//        log.info("ID {}", claims.get("id"));
//
//        Long userId = Long.parseLong(claims.get("id").toString());
//        Agent agent = agentRepository.findById(userId).orElse(null);
//        if(agent == null){
//            throw new UsernameNotFoundException("Invalid ID");
//        }
//        return agent;
//    }

    public SuccessDTO updatePassword(PasswordDTO pwd) throws Exception {
        int res = applicationRepository.updatePasswordByIdAndStatus(pwd.getPassword(),pwd.getApp_id(), ApplicationStatus.Approved);
        if(res != 0){
            return new SuccessDTO("password set successfully");
        }
        throw  new Exception("Application id incorrect or not approved");

    }
}