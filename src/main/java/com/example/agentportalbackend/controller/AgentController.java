package com.example.agentportalbackend.controller;

import com.example.agentportalbackend.config.AccessTokenRequired;
import com.example.agentportalbackend.dto.ErrorDTO;
import com.example.agentportalbackend.dto.PasswordDTO;
import com.example.agentportalbackend.dto.SuccessDTO;
import com.example.agentportalbackend.model.Application;
import com.example.agentportalbackend.repository.ApplicationRepository;
import com.example.agentportalbackend.service.AgentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequestMapping("/api/agent")
public class AgentController {
    private final ApplicationRepository applicationRepository;

    private final AgentService agentService;


    public AgentController(AgentService agentService,
                           ApplicationRepository applicationRepository) {
            this.agentService = agentService;
        this.applicationRepository = applicationRepository;
    }

        @GetMapping("/profile")
        @AccessTokenRequired
        public ResponseEntity<?> getAgentProfile(@RequestHeader(required = true) String token) {
            log.info("Agent profile view received");
            try {
                Application app = agentService.getProfile(token);
                return new ResponseEntity<>(app, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new Error(e.getMessage(),e), HttpStatus.CONFLICT);
            }
        }

    @PutMapping("/set-password")
    public ResponseEntity<?> setPasswordAgentProfile( @RequestBody PasswordDTO pwd) {
        log.info("Agent password update received");
        try {
            SuccessDTO success = agentService.updatePassword(pwd);
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage(),e.getStackTrace().toString()), HttpStatus.CONFLICT);
        }

    }
    @PutMapping("/profile-update")
    @AccessTokenRequired
    public ResponseEntity<?> updateAgentProfile(@RequestHeader(required = true) String token, @RequestBody Application app) {
        log.info("Agent profile update received");
        try {
            SuccessDTO success  = agentService.updateProfile(app);
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Error(e.getMessage(),e), HttpStatus.CONFLICT);
        }
    }

    }
