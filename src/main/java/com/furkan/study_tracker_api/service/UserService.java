package com.furkan.study_tracker_api.service;

import com.furkan.study_tracker_api.dto.UserDto;
import com.furkan.study_tracker_api.dto.UserUpdateDto;
import com.furkan.study_tracker_api.exception.ResourceNotFoundException;
import com.furkan.study_tracker_api.exception.ValidationException;
import com.furkan.study_tracker_api.util.UpdateUserMapper;
import com.furkan.study_tracker_api.util.UserMapper;
import com.furkan.study_tracker_api.model.User;
import com.furkan.study_tracker_api.repository.ActivityRepository;
import com.furkan.study_tracker_api.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UpdateUserMapper updateUserMapper;

    @Autowired
    public UserService(
            ActivityRepository activityRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            UserMapper userMapper, UpdateUserMapper updateUserMapper
    ){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.updateUserMapper = updateUserMapper;
    }

    public User getUser(Long id){
        return this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found"));
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User createUser(UserDto userDto){
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new ValidationException("Email already exists");
        } else if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new ValidationException("Username already exists");
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(UserUpdateDto userUpdateDto, Long id){

        if(userRepository.existsByUsername(userUpdateDto.getUsername()))
            throw new ValidationException("Username already exists");
        if(userRepository.existsByEmail(userUpdateDto.getEmail()))
            throw new ValidationException("Email already exists");
        if(userUpdateDto.getPassword() != null)
            userUpdateDto.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));

        User user = this.getUser(id);
        updateUserMapper.updateUserFromUpdateDto(userUpdateDto, user);
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        User user = this.getUser(id);
        userRepository.deleteById(id);
    }
}
