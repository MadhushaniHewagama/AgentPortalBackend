package com.example.agentportalbackend.service;

import com.example.agentportalbackend.model.Application;
import com.example.agentportalbackend.model.PersonalInfo;
import com.example.agentportalbackend.repository.ApplicationRepository;
import com.example.agentportalbackend.repository.PersonalInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final PersonalInfoRepository personalInfoRepository;

    public ApplicationService(ApplicationRepository applicationRepository, PersonalInfoRepository personalInfoRepository) {
        this.applicationRepository = applicationRepository;
        this.personalInfoRepository = personalInfoRepository;
    }

    public Application validate(Application app) throws Exception {
        PersonalInfo personalInfo = personalInfoRepository.getAllByFirstnameAndLastname(app.getPersonalInfo().getFirstname(),app.getPersonalInfo().getLastname());
        if(personalInfo == null){
            throw  new Exception("Application already exists");
        }
        return app;
    }

    public Application save(Application application) {
        Application savedApp = applicationRepository.save(application);
        return  savedApp;
    }
}
