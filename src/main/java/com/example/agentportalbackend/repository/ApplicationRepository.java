package com.example.agentportalbackend.repository;

import com.example.agentportalbackend.enums.ApplicationStatus;
import com.example.agentportalbackend.model.Address;
import com.example.agentportalbackend.model.Application;
import com.example.agentportalbackend.model.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Transactional
    @Modifying
    @Query("update Application a set a.contactInfo = ?1, a.addressList = ?2 where a.id = ?3")
    int updateContactInfoAndAddressListById(ContactInfo contactInfo, Address addressList, Long id);
     List<Application> findByStatusIn(Collection<ApplicationStatus> Statuses);


}
