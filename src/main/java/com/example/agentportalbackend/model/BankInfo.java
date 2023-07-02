package com.example.agentportalbackend.model;


import javax.persistence.*;

@Entity
@Table(name = "BANK_info")
public class BankInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String bankCode;

    @Column(nullable = false)
    private String branchCode;

    @Column(nullable = false)
    private String accountNumber;
}

