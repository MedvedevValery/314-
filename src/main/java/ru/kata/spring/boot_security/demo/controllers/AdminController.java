package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    private final RoleService roleService;

    public AdminController(UserService userService
            , RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

    @GetMapping
    public String showAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("allUsers", users);
        return "/admin/all_users_view";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAll());
        return "/admin/user_form";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/updateUser")
    public String updateUser(@RequestParam("userId") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAll());
        return "/admin/user_form";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") Long id) {
        userService.deleteUser(id);

        return "redirect:/admin";
    }
}