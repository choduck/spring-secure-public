package com.cloud.banking;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api")
public class SecureController {

    @GetMapping("/data")
    @PreAuthorize("hasAuthority('SCOPE_$XSAPPNAME.Display')")
    public String getData(Authentication authentication) {
        authentication.getAuthorities().forEach(authority -> 
            System.out.println("Authority: " + authority.getAuthority())
        );
        return "데이터 조회";
    }

    @PostMapping("/data")
    @PreAuthorize("hasAuthority('SCOPE_$XSAPPNAME.Create')")
    public String createData() {
        return "데이터 생성";
    }

    @GetMapping("/my-role")
    public String getMyRole(Authentication authentication) {
        boolean isViewer = authentication.getAuthorities()
            .stream()
            .anyMatch(a -> a.getAuthority().equals("SCOPE_$XSAPPNAME.Display"));
        
        boolean isEditor = authentication.getAuthorities()
            .stream()
            .anyMatch(a -> a.getAuthority().equals("SCOPE_$XSAPPNAME.Create"));

        if (isEditor) return "You are an Editor";
        if (isViewer) return "You are a Viewer";
        return "No specific role";
    }
}
