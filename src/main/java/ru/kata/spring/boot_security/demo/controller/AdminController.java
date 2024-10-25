package ru.kata.spring.boot_security.demo.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.spring.model.Role;
import ru.kata.spring.boot_security.demo.spring.model.RoleEnum;
import ru.kata.spring.boot_security.demo.spring.model.User;
import ru.kata.spring.boot_security.demo.spring.service.RoleService;
import ru.kata.spring.boot_security.demo.spring.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;


    public AdminController(UserService userService, @Qualifier("serviceRole") RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping()
    public String getStartPage(Model model) {
        List<User> userList = userService.getUserList();
        model.addAttribute("list", userList);
        return "index";
    }

    @GetMapping("/user/add")
    public String getAddUserPage(@ModelAttribute("newuser") User users, Model model ) {
        //users.setAge(1);
        List<Role> rolesList = roleService.getRoleList();
        model.addAttribute("roles", rolesList);
        return "adduser";
    }

    @PostMapping("/user/add")
    public String addUser(@Valid @ModelAttribute("newuser") User users, BindingResult result) {
        String s = "";
        User userdfdf = users;
        if (result.hasErrors()) {
            return "adduser";
        } else {
            User userTmp = userService.getUserByUsername(users.getUsername()).get();
            if (userTmp != null) {
                result.rejectValue("username", "логин уже существует",
                        "Ошибка ввода логина");
            } else {
                userService.addUser(users);
            }
            return "redirect:/admin";
        }
    }

    @GetMapping("/user/edit")
    public String getEditUserPage(@RequestParam("userId") String id, Model model) {
        User userTmp = userService.getUser(id).get();
        model.addAttribute("edituser", userTmp);
        return "edituser";
    }

    @PostMapping("/user/edit")
    public String editUser(@ModelAttribute("edituser") @Valid User users, BindingResult result,
                           @RequestParam("userId") String id) {
        if (result.hasErrors()) {
            return "edituser";
        } else {
            userService.updateUser(id, users);
        }
        return "redirect:/";
    }

    @GetMapping("user/delete")
    public String deleteUser(@RequestParam("userId") String id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
