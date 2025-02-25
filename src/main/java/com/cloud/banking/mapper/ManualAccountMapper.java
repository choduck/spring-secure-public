package com.cloud.banking.mapper;

import com.cloud.banking.dto.ManualAccount;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ManualAccountMapper {
    
    @Select("SELECT * FROM manual_account")
    List<ManualAccount> findAll();
    
    @Select("SELECT * FROM manual_account WHERE id = #{id}")
    ManualAccount findById(Long id);
    
    @Insert("INSERT INTO manual_account (account_number, bank_code, account_holder, " +
            "account_type, status, description, reg_date, reg_user) " +
            "VALUES (#{accountNumber}, #{bankCode}, #{accountHolder}, " +
            "#{accountType}, #{status}, #{description}, #{regDate}, #{regUser})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(ManualAccount account);
    
    @Update("UPDATE manual_account SET account_number = #{accountNumber}, " +
            "bank_code = #{bankCode}, account_holder = #{accountHolder}, " +
            "account_type = #{accountType}, status = #{status}, " +
            "description = #{description}, mod_date = #{modDate}, " +
            "mod_user = #{modUser} WHERE id = #{id}")
    void update(ManualAccount account);
} 