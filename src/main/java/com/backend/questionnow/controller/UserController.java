package com.backend.questionnow.controller;

import com.backend.questionnow.dto.UserLoginDto;
import com.backend.questionnow.dto.UserRegisterDto;
import com.backend.questionnow.security.CustomException;
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

    @PostMapping("/signIn")
    public ResponseEntity login(@RequestBody UserLoginDto userLoginDto, HttpServletResponse httpServletResponse) {
        try{
            userService.signIn(userLoginDto,httpServletResponse);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (CustomException e)
        {
            return new ResponseEntity(e.getMessage(),e.getHttpStatus());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity register(@RequestBody UserRegisterDto userRegisterDto)
    {
        try{
            userService.signUp(userRegisterDto);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        catch (CustomException e)
        {
            return new ResponseEntity(e.getName()+" "+ e.getMessage(),e.getHttpStatus());
        }
    }
}
