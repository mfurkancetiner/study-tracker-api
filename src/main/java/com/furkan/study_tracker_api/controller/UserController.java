package com.furkan.study_tracker_api.controller;

import com.furkan.study_tracker_api.dto.UserDto;
import com.furkan.study_tracker_api.dto.UserUpdateDto;
import com.furkan.study_tracker_api.model.User;
import com.furkan.study_tracker_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id){
        User user = this.userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDto userDto){
        User user = this.userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = this.userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        this.userService.deleteUser(id);
        return new ResponseEntity<>("User has been deleted", HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody @Valid UserUpdateDto userUpdateDto, @PathVariable("id") Long id){
        User user = this.userService.updateUser(userUpdateDto, id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
