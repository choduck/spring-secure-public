package com.cloud.banking.controller;

import com.cloud.banking.dto.ManualAccount;
import com.cloud.banking.dto.NormalAccount;
import com.cloud.banking.mapper.NormalAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/accounts")
public class ManualAccountController {

    @Autowired
    private NormalAccountMapper normalAccountMapper;

    @GetMapping("/manual")
    public String listAccounts(Model model) {
        return "accounts/manual";
    }

    @PostMapping("/manual")
    public String saveAccount(@ModelAttribute ManualAccount account) {
        // 저장 로직 생략
        return "redirect:/accounts/manual";
    }

    @GetMapping("/manual/{id}")
    public String editAccount(@PathVariable Long id, Model model) {
        // 임시 데이터 반환
        ManualAccount account = new ManualAccount();
        account.setId(id);
        model.addAttribute("account", account);
        return "accounts/edit";
    }

    @PostMapping("/manual/{id}")
    public String updateAccount(@PathVariable Long id, @ModelAttribute ManualAccount account) {
        // 수정 로직 생략
        return "redirect:/accounts/manual";
    }

    // 1. 수기계좌등록 - 일반계좌만 실제 화면, 나머지는 작업중
    @GetMapping("/register/normal")
    public String registerNormal(
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String financialInstitution,
            @RequestParam(required = false) String currencyCode,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String dateRange,
            @RequestParam(required = false) String accountNumber,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        
        // 검색 조건 설정
        Map<String, Object> searchParams = new HashMap<>();
        
        if (companyName != null && !companyName.isEmpty()) {
            searchParams.put("companyName", companyName);
        }
        
        if (financialInstitution != null && !financialInstitution.isEmpty()) {
            searchParams.put("financialInstitution", financialInstitution);
        }
        
        if (currencyCode != null && !currencyCode.isEmpty()) {
            searchParams.put("currencyCode", currencyCode);
        }
        
        if (accountNumber != null && !accountNumber.isEmpty()) {
            searchParams.put("accountNumber", accountNumber);
        }
        
        // 날짜 범위 처리
        if (dateRange != null && !dateRange.isEmpty()) {
            LocalDate endLocalDate = LocalDate.now();
            LocalDate startLocalDate = endLocalDate.minusDays(Integer.parseInt(dateRange));
            
            searchParams.put("startDate", startLocalDate.toString());
            searchParams.put("endDate", endLocalDate.toString());
        } else {
            if (startDate != null && !startDate.isEmpty()) {
                searchParams.put("startDate", startDate);
            }
            
            if (endDate != null && !endDate.isEmpty()) {
                searchParams.put("endDate", endDate);
            }
        }
        
        // 페이지 번호는 1부터 시작하지만, offset은 0부터 시작
        int offset = (page - 1) * size;
        
        // 검색 조건에 따른 계좌 목록 조회 (페이징 처리 포함)
        List<NormalAccount> accounts;
        int totalAccounts;
        
        if (searchParams.isEmpty()) {
            accounts = normalAccountMapper.findWithPaging(offset, size);
            totalAccounts = normalAccountMapper.countAll();
        } else {
            // 검색 조건이 있는 경우에도 페이징 처리 필요
            searchParams.put("offset", offset);
            searchParams.put("pageSize", size);
            accounts = normalAccountMapper.findBySearchCriteria(searchParams);
            totalAccounts = normalAccountMapper.countBySearchCriteria(searchParams);
        }
        
        // 전체 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalAccounts / size);
        
        // 드롭다운 목록 데이터 준비
        List<String> companies = Arrays.asList(
            "(주)삼성전자", "(주)현대자동차", "(주)LG전자", "(주)SK하이닉스", 
            "(주)네이버", "(주)카카오", "(주)포스코", "(주)롯데케미칼", 
            "(주)한화솔루션", "(주)신한금융지주"
        );
        
        List<String> banks = Arrays.asList(
            "한국은행", "산업은행", "기업은행", "국민은행", "하나은행", "수협은행"
        );
        
        model.addAttribute("accounts", accounts);
        model.addAttribute("companies", companies);
        model.addAttribute("banks", banks);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalAccounts", totalAccounts);
        
        return "accounts/register/normal";
    }

    // 일반계좌 등록 처리
    @PostMapping("/register/normal")
    public String saveNormalAccount(@ModelAttribute NormalAccount account) {
        normalAccountMapper.insert(account);
        return "redirect:/accounts/register/normal";
    }

    @GetMapping("/register/securities")
    public String registerSecurities(Model model) {
        return "accounts/work-in-progress";
    }

    @GetMapping("/register/loan")
    public String registerLoan(Model model) {
        return "accounts/register/loan";
    }

    // 2. 수기계좌 잔액조회 - 모두 작업중
    @GetMapping("/balance/normal")
    public String balanceNormal(Model model) {
        return "accounts/work-in-progress";
    }

    @GetMapping("/balance/securities")
    public String balanceSecurities(Model model) {
        return "accounts/work-in-progress";
    }

    @GetMapping("/balance/loan")
    public String balanceLoan(Model model) {
        return "accounts/work-in-progress";
    }

    // 3. 수기계좌 내역관리 - 작업중
    @GetMapping("/history")
    public String history(Model model) {
        return "accounts/work-in-progress";
    }

    // 대출계좌 관련 메서드 추가
    @GetMapping("/register/loan/limit")
    public String registerLoanLimit(Model model) {
        return "accounts/work-in-progress";
    }

    @GetMapping("/register/loan/execution")
    public String registerLoanExecution(Model model) {
        return "accounts/work-in-progress";
    }
} 