package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String usersPage(Model model) {
        model.addAttribute("listUser", userService.index());
        return "admin/users";
    }

    @GetMapping("/show")
    public String show(@RequestParam(value = "id") int id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "admin/show";
    }

//    @GetMapping("/new")
//    public String newUser(@ModelAttribute("user") User user) {
//        return "admin/new";
//    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "admin/new";

        userService.save(user);
        return "redirect:admin/users";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(value = "id") int id) {
        model.addAttribute("user", userService.show(id));
        return "admin/edit";
    }

    @PutMapping("/edit")
    public String update(@ModelAttribute("user") User user, BindingResult bindingResult,
                         @RequestParam(value = "id") int id) {
        if (bindingResult.hasErrors())
            return "admin/edit";

        userService.update(id, user);
        return "redirect:admin/users";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam(value = "id") int id) {
        userService.delete(id);
        return "redirect:admin/users";
    }
}