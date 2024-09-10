package com.furkan.study_tracker_api.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.furkan.study_tracker_api.dto.LoginDto;
import com.furkan.study_tracker_api.dto.UserDto;
import com.furkan.study_tracker_api.exception.ResourceNotFoundException;
import com.furkan.study_tracker_api.exception.ValidationException;
import com.furkan.study_tracker_api.model.AppUser;
import com.furkan.study_tracker_api.repository.UserRepository;
import com.furkan.study_tracker_api.service.JwtService;
import com.furkan.study_tracker_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("register")
    public ResponseEntity<AppUser> registerUser(@RequestBody @Valid UserDto userDto){
        if(userRepository.existsByEmail(userDto.getEmail()))
            throw new ValidationException("This email is taken");
        if(userRepository.existsByUsername(userDto.getUsername()))
            throw new ValidationException("This username is taken");
        AppUser user = this.userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<Object> loginUser(@RequestBody @Valid LoginDto loginDto){
        AppUser appUser =  userRepository.findByEmail(loginDto.getEmail());
        if(appUser == null)
            throw new ResourceNotFoundException("Wrong username or password");
        if(passwordEncoder.matches(appUser.getPassword(), loginDto.getPassword()))
            throw new ResourceNotFoundException("Wrong username or password");

        String token = jwtService.generateToken(appUser.getEmail(), appUser.getId());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
