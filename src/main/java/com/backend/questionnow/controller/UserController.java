package com.backend.questionnow.controller;

import com.backend.questionnow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public String login(
            @RequestParam String username,
            @RequestParam String password) {

        return userService.signin(username, password);
    }
}
