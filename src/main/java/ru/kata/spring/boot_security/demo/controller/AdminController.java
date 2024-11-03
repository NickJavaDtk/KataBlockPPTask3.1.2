package ru.kata.spring.boot_security.demo.controller;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.domain.model.Role;
import ru.kata.spring.boot_security.demo.domain.model.User;
import ru.kata.spring.boot_security.demo.domain.service.RoleService;
import ru.kata.spring.boot_security.demo.domain.service.UserService;


import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping()
    public String getStartPage(Model model) {
        List<User> userList = userService.getUserList();
        model.addAttribute("list", userList);
        return "admin/index";
    }

    @GetMapping("/user/add")
    public String getAddUserPage(@ModelAttribute("newuser") User users, Model model) {
        List<Role> rolesList = roleService.getRoleList();
        model.addAttribute("roles", rolesList);
        return "admin/adduser";
    }

    @PostMapping("/user/add")
    public String addUser(@Valid @ModelAttribute("newuser") User users, BindingResult result, Model model) {
        String checkFieldAddUser = userService.getCheckFieldAddUser(users, result, model, roleService);
        if (checkFieldAddUser == null) {
            userService.addUser(users);
            return "redirect:/admin";
        }
        return checkFieldAddUser;
    }

    @GetMapping("/user/edit")
    public String getEditUserPage(@RequestParam("userId") String id, Model model) {
        User userTmp = userService.getUser(id).get();
        Set<Role> roles = userTmp.getRoleSet();
        model.addAttribute("edituser", userTmp);
        model.addAttribute("roles", roles);
        return "admin/edituser";
    }

    @PostMapping("/user/edit")
    public String editUser(@ModelAttribute("edituser") @Valid User users, BindingResult result,
                           @RequestParam("userId") String id, Model model) {
        String checkFieldEditUser = userService.getCheckFieldEditUser(users, result, model, roleService);
        if (checkFieldEditUser == null) {
            userService.updateUser(id, users);
            return "redirect:/admin";
        }
        return checkFieldEditUser;
    }

    @GetMapping("user/delete")
    public String deleteUser(@RequestParam("userId") String id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
