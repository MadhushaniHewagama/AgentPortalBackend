package com.example.agentportalbackend.controller;
import com.example.agentportalbackend.dto.Error;
import com.example.agentportalbackend.dto.Token;
import com.example.agentportalbackend.dto.User;
import com.example.agentportalbackend.model.Agent;
import com.example.agentportalbackend.service.AuthService;
import com.example.agentportalbackend.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;


    public AuthController(AuthService authService, TokenService tokenService) {
        this.authService = authService;
        this.tokenService = tokenService;
    }

    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        log.info("Login Request received");
        try {
            Token token =  authService.login(user);
            token.setToken(tokenService.generateToken(token));
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Error(e.getMessage(),e.getStackTrace().toString()), HttpStatus.CONFLICT);
        }
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Agent agent) {
        log.info("Registration Request received");
        try{
            Agent savedAgent =  authService.register(agent);
            return new ResponseEntity<>(savedAgent,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new Error(e.getMessage(),e.getStackTrace().toString()), HttpStatus.CONFLICT);
        }
    }
}
