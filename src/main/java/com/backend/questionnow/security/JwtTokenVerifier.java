package com.backend.questionnow.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenVerifier extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        if (Strings.isEmpty(authorizationHeader) || authorizationHeader==null || !authorizationHeader.startsWith("Bearer "))
        {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        try{
            String key="dmkasldn28emkasldn28edn28emkadn28emka28emkasldn28edn28emkadn28emka28e4u180y4h21be2193h21one";
            Jws<Claims> claimsJws = Jwts.parser()
        }
    }
}
