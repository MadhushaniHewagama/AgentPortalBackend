package com.example.agentportalbackend.repository;

import com.example.agentportalbackend.model.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoRepository  extends JpaRepository<PersonalInfo, Long> {

     PersonalInfo getAllByFirstnameAndLastname(String firstname, String lastname);
}
