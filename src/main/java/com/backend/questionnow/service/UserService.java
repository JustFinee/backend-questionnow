package com.backend.questionnow.service;

import com.backend.questionnow.entity.User;
import com.backend.questionnow.repository.UserRepository;
import com.backend.questionnow.security.AuthException;
import com.backend.questionnow.security.JwtTokenProvider;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void signin(String username, String password, HttpServletResponse httpServletResponse) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtTokenProvider.createToken(username, userRepository.findByEmail(username).getRoles());
            httpServletResponse.addHeader("Token",token);
        } catch (AuthenticationException e) {
            throw new AuthException("Invalid username/password supplied", HttpStatus.UNAUTHORIZED);
        }
    }

    public User findUser(String email) {
        return userRepository.findByEmail(email);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long userId) throws NotFoundException
    {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() -> new NotFoundException("Not Found user with id: "+userId ));
    }


}
