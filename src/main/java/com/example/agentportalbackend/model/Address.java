
package com.example.agentportalbackend.model;


import com.example.agentportalbackend.enums.AddressType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String isPrimary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AddressType AddressType;

    @Column(nullable = false)
    private String addressLine01;

    @Column(nullable = false)
    private String addressLine02;

    @Column(nullable = true)
    private String addressLine03;

    @Column(nullable = true)
    private String state;

    @Column(nullable = true)
    private String country;

    @Column(nullable = false)
    private String zipCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public AddressType getAddressType() {
        return AddressType;
    }

    public void setAddressType(com.example.agentportalbackend.enums.AddressType addressType) {
        AddressType = addressType;
    }

    public String getAddressLine01() {
        return addressLine01;
    }

    public void setAddressLine01(String addressLine01) {
        this.addressLine01 = addressLine01;
    }

    public String getAddressLine02() {
        return addressLine02;
    }

    public void setAddressLine02(String addressLine02) {
        this.addressLine02 = addressLine02;
    }

    public String getAddressLine03() {
        return addressLine03;
    }

    public void setAddressLine03(String addressLine03) {
        this.addressLine03 = addressLine03;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(String isPrimary) {
        this.isPrimary = isPrimary;
    }
}
