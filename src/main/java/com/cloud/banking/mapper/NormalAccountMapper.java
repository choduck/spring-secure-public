package com.cloud.banking.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.cloud.banking.dto.NormalAccount;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NormalAccountMapper {
    
    // XML에 정의된 SQL을 사용하는 메서드들
    List<NormalAccount> findAll();
    
    // 페이징 처리를 위한 메서드 추가
    List<NormalAccount> findWithPaging(@Param("offset") int offset, @Param("pageSize") int pageSize);
    
    // 전체 계좌 수 조회
    int countAll();
    
    NormalAccount findById(Long accountId);
    
    int insert(NormalAccount account);
    
    // 검색 조건에 따른 계좌 목록 조회
    List<NormalAccount> findBySearchCriteria(Map<String, Object> params);
    
    // 검색 조건에 따른 계좌 수 조회
    int countBySearchCriteria(Map<String, Object> params);
} 