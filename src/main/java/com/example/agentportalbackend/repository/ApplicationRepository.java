package com.example.agentportalbackend.repository;

import com.example.agentportalbackend.enums.ApplicationStatus;
import com.example.agentportalbackend.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStatusIn(Collection<ApplicationStatus> Statuses);


}
