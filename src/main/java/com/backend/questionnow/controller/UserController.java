package com.backend.questionnow.controller;

import com.backend.questionnow.dto.UserLoginDto;
import com.backend.questionnow.security.AuthException;
import com.backend.questionnow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity login(@RequestBody UserLoginDto userLoginDto, HttpServletResponse httpServletResponse) {
        try{
            userService.signin(userLoginDto.getEmail(),userLoginDto.getPassword(),httpServletResponse);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (AuthException e)
        {
            return new ResponseEntity(e.getMessage(),e.getHttpStatus());
        }
    }
}
