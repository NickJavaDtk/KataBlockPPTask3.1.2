package ru.kata.spring.boot_security.demo.controller;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.security.UserDetailsImp;
import ru.kata.spring.boot_security.demo.spring.model.User;
import ru.kata.spring.boot_security.demo.spring.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class SecurityController {
    private UserService service;

    public SecurityController(UserService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

//    @PostMapping("/login")
//    public void autorization(Principal principal) {
//        String dd = principal.getName();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        List<? extends GrantedAuthority> us = (List<? extends GrantedAuthority>) authentication.getAuthorities();
//        User user =  authentication == null ? null : (User) authentication.getPrincipal();
//        String s = "";
//        //boolean b = service.checkUserLoginPasswordExist(login, password);
//        char c;
//    }
}
