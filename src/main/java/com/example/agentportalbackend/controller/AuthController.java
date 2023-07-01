package com.example.agentportalbackend.controller;
import com.example.agentportalbackend.dto.Token;
import com.example.agentportalbackend.dto.User;
import com.example.agentportalbackend.model.Agent;
import com.example.agentportalbackend.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity<Token> loginUser(@RequestBody User user) {
        log.info("Login Request received");
        Token token =  authService.login(user);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<Agent> registerUser(@RequestBody Agent agent) {
        log.info("Registration Request received");
        Agent savedAgent =  authService.register(agent);
        return new ResponseEntity<>(savedAgent,HttpStatus.OK);
    }
}
