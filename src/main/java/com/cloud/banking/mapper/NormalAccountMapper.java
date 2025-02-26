package com.cloud.banking.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.cloud.banking.dto.NormalAccount;

@Mapper
public interface NormalAccountMapper {
    
    // XML에 정의된 SQL을 사용하는 메서드들
    List<NormalAccount> findAll();
    
    NormalAccount findById(Long accountId);
    
    int insert(NormalAccount account);
    
    // 검색 조건에 따른 계좌 목록 조회
    List<NormalAccount> findBySearchCriteria(Map<String, Object> params);
} 