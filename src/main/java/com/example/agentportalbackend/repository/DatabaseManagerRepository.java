package com.example.agentportalbackend.repository;

import com.example.agentportalbackend.model.DatabaseManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseManagerRepository extends JpaRepository<DatabaseManager, Long> {
    DatabaseManager findByUsername(String username);

    DatabaseManager findByUsernameAndPassword(String username, String password);
}