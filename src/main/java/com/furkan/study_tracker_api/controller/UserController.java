package com.furkan.study_tracker_api.controller;

import com.furkan.study_tracker_api.dto.UserDto;
import com.furkan.study_tracker_api.dto.UserUpdateDto;
import com.furkan.study_tracker_api.model.AppUser;
import com.furkan.study_tracker_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<AppUser> getUser(@PathVariable("id") Long id){
        AppUser user = this.userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AppUser>> getAllUsers(){
        List<AppUser> users = this.userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        this.userService.deleteUser(id);
        return new ResponseEntity<>("User has been deleted", HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(@RequestBody @Valid UserUpdateDto userUpdateDto, @PathVariable("id") Long id){
        AppUser user = this.userService.updateUser(userUpdateDto, id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
