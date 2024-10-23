package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.spring.service.UserService;

@Controller
public class SecurityController {
    private UserService service;

    public SecurityController(UserService service) {
        this.service = service;
    }

//    @GetMapping("/login")
//    public String getLoginPage() {
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public void autorization(@RequestParam String login, @RequestParam String password) {
//        String s = "";
//        boolean b = service.checkUserLoginPasswordExist(login, password);
//        char c;
//    }
}
