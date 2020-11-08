package com.backend.questionnow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDto extends UserLoginDto {
    private String name;
}
