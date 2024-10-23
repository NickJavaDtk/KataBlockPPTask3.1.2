package ru.kata.spring.boot_security.demo.controller;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.spring.model.User;
import ru.kata.spring.boot_security.demo.spring.service.UserService;


import java.util.List;

@Controller
@RequestMapping(produces = "text/plain; charset=UTF-8")
public class UserController {

    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String getStartPage(Model model) {
        List<User> userList = userService.getUserList();
        model.addAttribute("list", userList);
        return "index";
    }

    @GetMapping("/user/add")
    public String getAddUserPage(@ModelAttribute("newuser") User users) {
        users.setAge(1);
        return "adduser";
    }

    @PostMapping("/user/add")
    public String addUser(@Valid @ModelAttribute("newuser") User users, BindingResult result) {
        if (result.hasErrors()) {
            return "adduser";
        } else {
            User user = users;
            userService.addUser(user);
            return "redirect:/";
        }
    }

    @GetMapping("/user/edit")
    public String getEditUserPage(@RequestParam("userId") String id, Model model) {
        User userTmp = userService.getUser(id);
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
