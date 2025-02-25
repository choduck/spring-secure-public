package com.cloud.banking;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/info")
    @ResponseBody
    public String getPublicInfo() {
        return "This is public information. No authentication required.";
    }
    
    @GetMapping("/hello")
    public String publicHello() {
        return "public-hello";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";  // 인증 없이 접근 가능한 상태 체크 엔드포인트
    }
}
