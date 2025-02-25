package com.sample.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
public class HelloController {

    // @GetMapping("/")
    // public String index() {
    //     return "Greetings from Spring Boot!!!!!";
    // }

    // @GetMapping("/hello")
    // public String hello() {
    //     return "Hello World!!!!!!";
    // }
    
    @GetMapping("/hello")
    public String hello(Principal principal) {
        System.out.println("Accessed /hello endpoint");
        if (principal != null) {
            System.out.println("Authenticated user: " + principal.getName());
            return "Hello, " + principal.getName() + "!";
        } else {
            System.out.println("Unauthenticated access");
            return "Hello, guest!";
        }
    }


}


