package com.example.agentportalbackend.repository;
import com.example.agentportalbackend.model.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {
    @Transactional
    @Modifying
    @Query("update ContactInfo c set c.phoneOffice = ?1, c.phonePersonal = ?2, c.email = ?3 where c.id = ?4")
    int updatePhoneOfficeAndPhonePersonalAndEmailById(String phoneOffice, String phonePersonal, String email, Long id);

}