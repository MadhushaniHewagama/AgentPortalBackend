package com.example.agentportalbackend.controller;

import com.example.agentportalbackend.config.AccessTokenRequired;
import com.example.agentportalbackend.dto.SuccessDTO;
import com.example.agentportalbackend.enums.Role;
import com.example.agentportalbackend.model.Application;
import com.example.agentportalbackend.repository.DatabaseManagerRepository;
import com.example.agentportalbackend.service.ApplicationService;
import com.example.agentportalbackend.service.EmailService;
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
    private final DatabaseManagerRepository databaseManagerRepository;

    private final ApplicationService applicationService;
    private final EmailService emailService;

    public ApplicationController(ApplicationService applicationService, EmailService emailService,
                                 DatabaseManagerRepository databaseManagerRepository) {
        this.applicationService = applicationService;
        this.emailService = emailService;
        this.databaseManagerRepository = databaseManagerRepository;
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
            emailService.sendSimpleMessage("'BackendUser@life.in.com","Newly submitted registration","<body>\n" +
                    "  <p>Hello,</p>\n" +
                    "  <p>A new application registration has been made. please see the details in bellow with attached documents.</p><br><br>\n" +
                    "  <p>Application registration Id:"+savedApplication.getId()+"</p>\n" +
                    "  <p>This is an auto generated email, please do not reply</p>\n" +
                    "\n" +
                    "</body>");
            emailService.sendSimpleMessage(application.getContactInfo().getEmail(),"Application submitting confirmation","<body>\n" +
                    "  \n" +
                    "  <p>Dear Applicant,</p>\n" +
                    "  <p>Thank you for submitting our application.</p>\n" +
                    "  \n" +
                    "  <p>We all send a confirmation very soon if your application is approved</p><br><br>\n" +
                    "  <p>This is an auto generated email, please do not reply</p>\n" +
                    "\n" +
                    "</body>");
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
            Application app =  applicationService.getByID(id,token);
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

    @RequestMapping("/approve")
    @PutMapping
    @AccessTokenRequired
    public ResponseEntity<?> approveApplication(@RequestHeader(required = true) String token,@RequestBody Application application) {
        log.info("Application Approved");
        try{
            Role role =  applicationService.approve(token,application);
            if(role == Role.BackendUser){
                emailService.sendSimpleMessage("AgencyManager@life.in.com","Application form with registartion id "+application.getId(),
                        "<p>\n" +
                                "    Hello,\n" +
                                "  </p>\n" +
                                "  <p>\n" +
                                "    Hello,Look over the registration id: "+application.getId()+"\n" +
                                "  </p><br />\n" +
                                "  <p>Best regards,</p>\n" +
                                "  <p>Agency Manager</p>");

            }else{
                emailService.sendSimpleMessage(application.getContactInfo().getEmail(),"Application submission confirmation",
                        "<p>\n" +
                                "    Dear Applicant,\n" +
                                "  </p>\n" +
                                "  <p>\n" +
                                "    Your application has been approved and username is "+application.getPersonalInfo().getFirstname()+application.getPersonalInfo().getLastname()+
                                ". Further below this email are the details that have been made following the approval of your application, Please continue finishing your appplication create by creating a new password and user with this link.\n <a href=http://localhost:3000/passwordCreation?id=" +
                                +application.getId() +"\">Click Here</a>"
                        +"\n"+
                                "  </p><br />\n" +
                                "  <p>Best regards,</p>\n" +
                                "  <p>Backend Manager</p>");

            }
            return new ResponseEntity<>(new SuccessDTO("approved application"), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ErrorDTO(e.getMessage(),e.getStackTrace().toString()), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping("/reject")
    @PutMapping
    @AccessTokenRequired
    public ResponseEntity<?> rejectApplication(@RequestHeader(required = true) String token,@RequestBody Application application) {
        log.info("Application Approved");
        try{
            Role role =  applicationService.reject(token,application);
            if(role == Role.BackendUser){
                emailService.sendSimpleMessage(application.getContactInfo().getEmail(),"Application Rejected",
                        "<p>\n" +
                                "    Dear Applicant,\n" +
                                "  </p>\n" +
                                "  <p>\n" +
                                "    Your application has been rejected.\n" +
                                "  </p><br />\n" +
                                "  <p>Best regards,</p>\n" +
                                "  <p>Backend Manager</p>");
            }else{
                emailService.sendSimpleMessage(application.getContactInfo().getEmail(),"Application Rejected",
                        "<p>\n" +
                                "    Dear Applicant,\n" +
                                "  </p>\n" +
                                "  <p>\n" +
                                "    Your application has been rejected.\n" +
                                "  </p><br />\n" +
                                "  <p>Best regards,</p>\n" +
                                "  <p>Agency Manager</p>");

            }
            return new ResponseEntity<>(new SuccessDTO("reject application"), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ErrorDTO(e.getMessage(),e.getStackTrace().toString()), HttpStatus.CONFLICT);
        }
    }
}
