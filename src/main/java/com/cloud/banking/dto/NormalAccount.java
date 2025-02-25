package com.cloud.banking.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NormalAccount {
    private Long accountId;
    private String companyName;
    private String groupCode;
    private String financialInstitution;
    private String accountNumber;
    private String accountAlias;
    private String accountType;
    private String currencyCode;
    private BigDecimal balance;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 