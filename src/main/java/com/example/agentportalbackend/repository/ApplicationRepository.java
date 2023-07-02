package com.example.agentportalbackend.repository;

import com.example.agentportalbackend.enums.ApplicationStatus;
import com.example.agentportalbackend.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Override
    Optional<Application> findById(Long aLong);
}
