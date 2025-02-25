package com.cloud.banking.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ManualAccount {
    private Long id;
    private String accountNumber;    // 계좌번호
    private String bankCode;         // 은행코드
    private String accountHolder;    // 예금주
    private String accountType;      // 계좌유형
    private String status;           // 상태
    private String description;      // 비고
    private LocalDateTime regDate;   // 등록일시
    private String regUser;          // 등록자
    private LocalDateTime modDate;   // 수정일시
    private String modUser;          // 수정자
} 