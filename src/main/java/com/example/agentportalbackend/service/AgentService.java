package com.example.agentportalbackend.service;

import com.example.agentportalbackend.dto.PasswordDTO;
import com.example.agentportalbackend.dto.SuccessDTO;
import com.example.agentportalbackend.model.Agent;
import com.example.agentportalbackend.model.Application;
import com.example.agentportalbackend.repository.AgentRepository;
import com.example.agentportalbackend.repository.ApplicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<Application> res = applicationRepository.findById(pwd.getApp_id());
        if(res.get() != null){
            agentRepository.save(new Agent((res.get().getPersonalInfo().getFirstname()+res.get().getPersonalInfo().getLastname()),pwd.getPassword(),res.get() ));
            return new SuccessDTO("password set successfully");
        }
        throw  new Exception("Application id incorrect or not approved");


    }
}