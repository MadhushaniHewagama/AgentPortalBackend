package com.example.agentportalbackend.repository;
import com.example.agentportalbackend.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    Agent findByUsernameAndPassword(String username, String password);

    @Override
    Optional<Agent> findById(Long aLong);
}