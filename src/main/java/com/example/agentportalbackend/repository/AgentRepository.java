package com.example.agentportalbackend.repository;
import com.example.agentportalbackend.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    Agent findByUsername(String username);

    Agent findByUsernameAndPassword(String username, String password);
}