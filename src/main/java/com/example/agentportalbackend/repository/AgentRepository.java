package com.example.agentportalbackend.repository;
import com.example.agentportalbackend.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    @Transactional
    @Modifying
    @Query("update Agent a set a.username = ?1, a.password = ?2, a.firstname = ?3, a.middlename = ?4, a.lastname = ?5, a.gender = ?6, a.dateOfBirth = ?7, a.ssn = ?8 " +
            "where a.id = ?9")
    int updateUsernameAndPasswordAndFirstnameAndMiddlenameAndLastnameAndGenderAndDateOfBirthAndSsnById(String username, String password, String firstname, String middlename, String lastname, String gender, String dateOfBirth, String ssn, Long id);

    Agent findByUsernameAndPassword(String username, String password);
}