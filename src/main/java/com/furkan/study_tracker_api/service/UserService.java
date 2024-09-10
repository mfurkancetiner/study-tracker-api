package com.furkan.study_tracker_api.service;

import com.furkan.study_tracker_api.dto.UserDto;
import com.furkan.study_tracker_api.dto.UserUpdateDto;
import com.furkan.study_tracker_api.exception.ResourceNotFoundException;
import com.furkan.study_tracker_api.exception.ValidationException;
import com.furkan.study_tracker_api.model.AppUser;
import com.furkan.study_tracker_api.util.UpdateUserMapper;
import com.furkan.study_tracker_api.util.UserMapper;
import com.furkan.study_tracker_api.repository.ActivityRepository;
import com.furkan.study_tracker_api.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UpdateUserMapper updateUserMapper;

    public AppUser getUser(Long id){
        return this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found"));
    }

    public List<AppUser> getAllUsers(){
        return this.userRepository.findAll();
    }

    public AppUser createUser(UserDto userDto){
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new ValidationException("Email already exists");
        } else if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new ValidationException("Username already exists");
        }
        AppUser user = new AppUser();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    public AppUser updateUser(UserUpdateDto userUpdateDto, Long id){

        if(userRepository.existsByUsername(userUpdateDto.getUsername()))
            throw new ValidationException("Username already exists");
        if(userRepository.existsByEmail(userUpdateDto.getEmail()))
            throw new ValidationException("Email already exists");
        if(userUpdateDto.getPassword() != null)
            userUpdateDto.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));

        AppUser user = this.getUser(id);
        updateUserMapper.updateUserFromUpdateDto(userUpdateDto, user);
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        AppUser user = this.getUser(id);
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
