package com.example.agentportalbackend.service;

import com.example.agentportalbackend.dto.PasswordDTO;
import com.example.agentportalbackend.dto.SuccessDTO;
import com.example.agentportalbackend.model.Address;
import com.example.agentportalbackend.model.Agent;
import com.example.agentportalbackend.model.Application;
import com.example.agentportalbackend.repository.AddressRepository;
import com.example.agentportalbackend.repository.AgentRepository;
import com.example.agentportalbackend.repository.ApplicationRepository;
import com.example.agentportalbackend.repository.ContactInfoRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AgentService {

    private String SECRET_KEY = "dshhsfhshdsdfgasdfasdadasdfagsfbhfgjfhjgdhfsfgbdafaf";

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ContactInfoRepository contactInfoRepository;

    public SuccessDTO updatePassword(PasswordDTO pwd) throws Exception {
        Optional<Application> res = applicationRepository.findById(pwd.getApp_id());
        if(res.get() != null){
            if(agentRepository.findByApplication_Id(pwd.getApp_id()) != null){
                throw  new Exception("Already generated password");
            }
            agentRepository.save(new Agent((res.get().getPersonalInfo().getFirstname()+res.get().getPersonalInfo().getLastname()),pwd.getPassword(),res.get() ));
            return new SuccessDTO("password set successfully");
        }
        throw  new Exception("Application id incorrect or not approved");


    }

    public Application getProfile(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        Long id = Long.parseLong((String) claims.get("id"));
        log.info("ID {}",id);
        return  agentRepository.findById(id).get().getApplication();
    }

    public SuccessDTO updateProfile(Application app) throws Exception {
        contactInfoRepository.updatePhoneOfficeAndPhonePersonalAndEmailById(app.getContactInfo().getPhoneOffice(),app.getContactInfo().getPhonePersonal(),
                app.getContactInfo().getEmail(),app.getContactInfo().getId());
        for(Address address: app.getAddressList()){
            addressRepository.updateIsPrimaryAndAddressTypeAndAddressLine01AndAddressLine02AndAddressLine03AndStateAndCountryAndZipCodeById(
                    address.getIsPrimary(),address.getAddressType(),address.getAddressLine01(), address.getAddressLine02(),
                    address.getAddressLine03(), address.getState(),address.getZipCode(),address.getCountry(),address.getId()
            );
        }
         return new SuccessDTO("Successfully updated profile");
    }
}