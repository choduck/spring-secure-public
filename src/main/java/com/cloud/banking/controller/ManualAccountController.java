package com.cloud.banking.controller;

import com.cloud.banking.dto.ManualAccount;
import com.cloud.banking.dto.NormalAccount;
import com.cloud.banking.mapper.NormalAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public String registerNormal(Model model, 
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "10") int size) {
        // 페이지 번호는 1부터 시작하지만, offset은 0부터 시작
        int offset = (page - 1) * size;
        
        // 페이징 처리된 계좌 목록 조회
        List<NormalAccount> accounts = normalAccountMapper.findWithPaging(offset, size);
        
        // 전체 계좌 수 조회
        int totalAccounts = normalAccountMapper.countAll();
        
        // 전체 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalAccounts / size);
        
        model.addAttribute("accounts", accounts);
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