package com.example.agentportalbackend.repository;

import com.example.agentportalbackend.enums.ApplicationStatus;
import com.example.agentportalbackend.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Transactional
    @Modifying
    @Query("update Application a set a.password = ?1 where a.id = ?2 and a.Status = ?3")
    int updatePasswordByIdAndStatus(String password, Long id, ApplicationStatus Status);
}
