package com.example.agentportalbackend.repository;

import com.example.agentportalbackend.enums.AddressType;
import com.example.agentportalbackend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Transactional
    @Modifying
    @Query("update Address a set a.isPrimary = ?1, a.AddressType = ?2, a.addressLine01 = ?3, a.addressLine02 = ?4, a.addressLine03 = ?5, a.state = ?6, a.country = ?7, a.zipCode = ?8 " +
            "where a.id = ?9")
    int updateIsPrimaryAndAddressTypeAndAddressLine01AndAddressLine02AndAddressLine03AndStateAndCountryAndZipCodeById(String isPrimary, AddressType AddressType, String addressLine01, String addressLine02, String addressLine03, String state, String country, String zipCode, Long id);

}