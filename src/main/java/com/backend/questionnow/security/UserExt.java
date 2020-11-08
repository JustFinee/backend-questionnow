package com.backend.questionnow.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class UserExt extends User {

    private Long userId;

    public UserExt(String username, String password, Collection<? extends GrantedAuthority> authorities, Long userId) {
        super(username, password, true, true, true, true, authorities);
        this.userId = userId;
    }

}


