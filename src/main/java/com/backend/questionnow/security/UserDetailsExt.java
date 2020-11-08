package com.backend.questionnow.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsExt extends UserDetails {

    String getUserId();
}
