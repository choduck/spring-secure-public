package com.cloud.banking;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PublicController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/public/info")
    @ResponseBody
    public String getPublicInfo() {
        return "This is public information. No authentication required.";
    }
    
    @GetMapping("/public/hello")
    public String publicHello() {
        return "public-hello";
    }
}
