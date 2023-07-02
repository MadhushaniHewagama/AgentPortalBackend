package com.example.agentportalbackend.controller;

import com.example.agentportalbackend.config.AccessTokenRequired;
import com.example.agentportalbackend.model.Application;
import com.example.agentportalbackend.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.agentportalbackend.dto.ErrorDTO;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequestMapping("/api/application")
public class ApplicationController {

    private final ApplicationService applicationService;


    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @RequestMapping("/validate")
    @PostMapping
    public ResponseEntity<?> validateApplication(@RequestBody Application application) {
        log.info("Application Request received");
        try{
            Application savedApplication =  applicationService.validate(application);
            return new ResponseEntity<>(savedApplication, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ErrorDTO(e.getMessage(),e.getStackTrace().toString()), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping("/save")
    @PostMapping
    public ResponseEntity<?> saveApplication(@RequestBody Application application) {
        log.info("Application Request saved");
        try{
            Application savedApplication =  applicationService.save(application);
            return new ResponseEntity<>(savedApplication, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ErrorDTO(e.getMessage(),e.getStackTrace().toString()), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping("/{id}")
    @GetMapping
    @AccessTokenRequired
    public ResponseEntity<?> getApplicationByID(@RequestHeader(required = true) String token, @PathVariable Long id) {
        log.info("Application get by id Request");
        try{
            Application app =  applicationService.getByID(id);
            return new ResponseEntity<>(app, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ErrorDTO(e.getMessage(),e.getStackTrace().toString()), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping("/all")
    @GetMapping
    @AccessTokenRequired
    public ResponseEntity<?> getAllApplication(@RequestHeader(required = true) String token) {
        log.info("Application get all Request");
        try{
            List<Application> applications =  applicationService.getAll(token);
            return new ResponseEntity<>(applications, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ErrorDTO(e.getMessage(),e.getStackTrace().toString()), HttpStatus.CONFLICT);
        }
    }
}
