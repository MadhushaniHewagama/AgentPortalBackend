package com.example.agentportalbackend.controller;

import com.example.agentportalbackend.config.AccessTokenRequired;
import com.example.agentportalbackend.dto.Error;
import com.example.agentportalbackend.model.Agent;
import com.example.agentportalbackend.service.AgentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequestMapping("/api/agent/profile")
public class AgentController {

        private final AgentService agentService;


        public AgentController(AgentService agentService) {
            this.agentService = agentService;
        }

        @GetMapping
        @AccessTokenRequired
        public ResponseEntity<?> getAgentProfile(@RequestHeader(required = true) String token) {
            log.info("Agent profile view received");
            try {
                Agent agent = agentService.getProfile(token);
                return new ResponseEntity<>(agent, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new Error(e.getMessage(),e.getStackTrace().toString()), HttpStatus.CONFLICT);
            }
        }

//    @PutMapping
//    @AccessTokenRequired
//    public ResponseEntity<?> updateAgentProfile(@RequestHeader(required = true) String token, @RequestBody Agent agent) {
//        log.info("Agent profile update received");
//        try {
//            Agent updatedAgent = agenntService.updateProfile(agent);
//            return new ResponseEntity<>(updatedAgent, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new Error(e.getMessage(),e.getStackTrace().toString()), HttpStatus.CONFLICT);
//        }
//
//    }

    }
