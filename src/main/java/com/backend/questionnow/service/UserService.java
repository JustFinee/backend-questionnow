package com.backend.questionnow.service;

import com.backend.questionnow.dto.UserLoginDto;
import com.backend.questionnow.dto.UserRegisterDto;
import com.backend.questionnow.entity.User;
import com.backend.questionnow.repository.UserRepository;
import com.backend.questionnow.security.CustomException;
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

    public void signIn(UserLoginDto userLoginDto, HttpServletResponse httpServletResponse) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(),userLoginDto.getPassword()));
            User user = userRepository.findByEmail(userLoginDto.getEmail());
            String token = jwtTokenProvider.createToken(userLoginDto.getEmail(), user.getRoles());
            httpServletResponse.addHeader("Access-Control-Expose-Headers", "token,id,name");
            httpServletResponse.addHeader("token",token);
            httpServletResponse.addHeader("id",String.valueOf(user.getUserId()));
            httpServletResponse.addHeader("name",user.getName());


        } catch (AuthenticationException e) {
            throw new CustomException("InvalidLoginCredentialsException","Invalid username/password supplied", HttpStatus.UNAUTHORIZED);
        }
    }

    public void signUp(UserRegisterDto userRegisterDto)
    {
        if (Optional.ofNullable(userRepository.findByEmail(userRegisterDto.getEmail())).isPresent())
        {
            throw new CustomException("EmailExistsException","Email already is used",HttpStatus.CONFLICT);
        }
        else
        {
            userRepository.save(new User(userRegisterDto.getName(),userRegisterDto.getEmail(),passwordEncoder.encode(userRegisterDto.getPassword())));
        }
    }

    public User findUserById(Long userId) throws CustomException
    {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() -> new CustomException("NotFoundUserException","Not Found user with id: "+userId,HttpStatus.NOT_FOUND ));
    }

    public void saveUser(User user)
    {
        userRepository.save(user);
    }


}
