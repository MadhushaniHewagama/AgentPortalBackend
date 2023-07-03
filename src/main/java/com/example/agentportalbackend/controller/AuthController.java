package com.example.agentportalbackend.controller;
import com.example.agentportalbackend.dto.ErrorDTO;
import com.example.agentportalbackend.dto.TokenDTO;
import com.example.agentportalbackend.dto.User;
import com.example.agentportalbackend.service.AuthService;
import com.example.agentportalbackend.service.EmailService;
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


    public AuthController(AuthService authService, TokenService tokenService, EmailService emailService) {
        this.authService = authService;
        this.tokenService = tokenService;
    }

    @PostMapping
    @RequestMapping("/agent/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        log.info("Agent Login Request received");
        try {
            TokenDTO tokenDTO =  authService.login(user);
            tokenDTO.setToken(tokenService.generateToken(tokenDTO));
            return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage(),e.getStackTrace().toString()), HttpStatus.CONFLICT);
        }
    }

    @PostMapping
    @RequestMapping("/other/login")
    public ResponseEntity<?> otherUser(@RequestBody User user) {
        log.info("Other Login Request received");
        try {
            TokenDTO tokenDTO =  authService.otherLogin(user);
            tokenDTO.setToken(tokenService.generateToken(tokenDTO));
            return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage(),e.getStackTrace().toString()), HttpStatus.CONFLICT);
        }
    }

}
