package com.example.agentportalbackend.model;

import com.example.agentportalbackend.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "personalInfo_id")
    private PersonalInfo personalInfo;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "contactInfo_id")
    private ContactInfo contactInfo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private List<Address> addressList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "educationalInfo_id")
    private List<EducationalInfo> educationalInfoList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "workExperiance_id")
    private List<WorkExperianceInfo> workExperianceInfoList;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "bankInfo_id")
    private BankInfo bankInfo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "document_id")
    private List<Document> documentList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<EducationalInfo> getEducationalInfoList() {
        return educationalInfoList;
    }

    public void setEducationalInfoList(List<EducationalInfo> educationalInfoList) {
        this.educationalInfoList = educationalInfoList;
    }

    public List<WorkExperianceInfo> getWorkExperianceInfoList() {
        return workExperianceInfoList;
    }

    public void setWorkExperianceInfoList(List<WorkExperianceInfo> workExperianceInfoList) {
        this.workExperianceInfoList = workExperianceInfoList;
    }

    public BankInfo getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(BankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }
}
