package com.example.agentportalbackend.service;

import com.example.agentportalbackend.enums.ApplicationStatus;
import com.example.agentportalbackend.enums.Role;
import com.example.agentportalbackend.model.Application;
import com.example.agentportalbackend.model.PersonalInfo;
import com.example.agentportalbackend.repository.ApplicationRepository;
import com.example.agentportalbackend.repository.PersonalInfoRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
@Slf4j
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final PersonalInfoRepository personalInfoRepository;
    private String SECRET_KEY = "dshhsfhshdsdfgasdfasdadasdfagsfbhfgjfhjgdhfsfgbdafaf";


    public ApplicationService(ApplicationRepository applicationRepository, PersonalInfoRepository personalInfoRepository) {
        this.applicationRepository = applicationRepository;
        this.personalInfoRepository = personalInfoRepository;
    }

    public Application validate(Application app) throws Exception {
        PersonalInfo personalInfo = personalInfoRepository.getAllByFirstnameAndLastname(app.getPersonalInfo().getFirstname(),app.getPersonalInfo().getLastname());
        if(personalInfo != null){
            throw  new Exception("Application already exists");
        }
        return app;
    }

    public Application save(Application application) {
        application.setStatus(ApplicationStatus.Submitted);
        Application savedApp = applicationRepository.save(application);
        return  savedApp;
    }

    public List<Application> getAll(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        Role role = Role.valueOf((String) claims.get("role"));
        if(role == Role.AgencyManager){
            log.info("Role {}",role);
            return applicationRepository.findByStatusIn(Arrays.asList(ApplicationStatus.Approved, ApplicationStatus.Rejected,ApplicationStatus.Initiated));
        }else{
            return applicationRepository.findByStatusIn(Arrays.asList(ApplicationStatus.Submitted,ApplicationStatus.Approved));
        }

    }

    public Application getByID(Long id) {
        return applicationRepository.findById(id).get();
    }

    public Role approve(String token, Application app) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        Role role = Role.valueOf((String) claims.get("role"));
        if(role == Role.AgencyManager){
            log.info("AgencyManager");
            applicationRepository.updateStatusById(ApplicationStatus.Approved,app.getId());
        }else{
            log.info("BackendManager");
            applicationRepository.updateStatusById(ApplicationStatus.Initiated,app.getId());
        }
        return role;
    }

    public Role reject(String token,  Application app) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        Role role = Role.valueOf((String) claims.get("role"));
        applicationRepository.updateStatusById(ApplicationStatus.Rejected,app.getId());
        return role;
    }
}
